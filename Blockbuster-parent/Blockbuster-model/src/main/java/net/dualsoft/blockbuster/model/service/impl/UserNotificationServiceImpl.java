/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service.impl;

import java.util.List;
import static net.dualsoft.blockbuster.db.jooq.Tables.USER_NOTIFICATION;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.UserNotification;
import net.dualsoft.blockbuster.model.helper.RabbitUtil;
import net.dualsoft.blockbuster.model.helper.pojos.Response;
import net.dualsoft.blockbuster.model.service.UserNotificationService;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author teodora
 */
@Service
public class UserNotificationServiceImpl implements UserNotificationService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private DSLContext context;

    @Override
    public Response<UserNotification> getUserNotification(int id) {
        List<UserNotification> userNotification = context.selectFrom(USER_NOTIFICATION).where(USER_NOTIFICATION.USER_NOTIFICATION_ID.eq(id)).fetchInto(UserNotification.class);
        if (userNotification.isEmpty()) {
            return new Response(null, "User notification not found", 404);
        }
        return new Response(userNotification.get(0), "Ok", 200);
    }

    @Override
    public Response<List<UserNotification>> getUsersNotification() {
        List<UserNotification> userNotification = context.selectFrom(USER_NOTIFICATION).fetchInto(UserNotification.class);
        if (userNotification.isEmpty()) {
            return new Response(null, "User notification not found", 404);
        }
        return new Response(userNotification, "Ok", 200);
    }

    @Override
    public Response<List<UserNotification>> getReadUsersNotification(int id) {
        List<UserNotification> userNotification = context
                .selectFrom(USER_NOTIFICATION)
                .where(USER_NOTIFICATION.READ.eq(Boolean.TRUE))
                .and(USER_NOTIFICATION.CUSTOMER_ID.eq(id))
                .fetchInto(UserNotification.class);
        if (userNotification.isEmpty()) {
            return new Response(null, "User notification not found", 404);
        }
        return new Response(userNotification, "Ok", 200);
    }

    @Override
    public Response<List<UserNotification>> getUneadUsersNotification(int id) {
        List<UserNotification> userNotification = context
                .selectFrom(USER_NOTIFICATION)
                .where(USER_NOTIFICATION.READ.eq(Boolean.FALSE))
                .and(USER_NOTIFICATION.CUSTOMER_ID.eq(id))
                .fetchInto(UserNotification.class);
        if (userNotification.isEmpty()) {
            return new Response(null, "User notification not found", 404);
        }
        return new Response(userNotification, "Ok", 200);
    }

    @Override
    public Response<List<UserNotification>> getNotificationForCustomer(int id) {
        List<UserNotification> userNotification = context
                .selectFrom(USER_NOTIFICATION)
                .where(USER_NOTIFICATION.CUSTOMER_ID.eq(id))
                .orderBy(USER_NOTIFICATION.LAST_UPDATE.desc())
                .fetchInto(UserNotification.class);
        if (userNotification.isEmpty()) {
            return new Response(null, "Notification for customer not found", 404);
        }
        return new Response(userNotification, "Ok", 200);
    }

    @Override
    public Response<List<UserNotification>> setNotificationToRead(int id) {
        int brRez = 0;

        brRez = context.update(USER_NOTIFICATION)
                .set(USER_NOTIFICATION.READ, true)
                .where(USER_NOTIFICATION.CUSTOMER_ID.eq(id))
                .and(USER_NOTIFICATION.READ.eq(false))
                .execute();

        if (brRez == 0) {
            return new Response(null, "Notification for customer not found", 404);
        }
        return new Response(brRez, "Ok", 200);
    }

    @Override
    public Response<Integer> unreadNotificationsCount(int id) {
        Integer count = context
                .select(DSL.count())
                .from(USER_NOTIFICATION)
                .where(USER_NOTIFICATION.CUSTOMER_ID.eq(id))
                .and(USER_NOTIFICATION.READ.eq(false))
                .fetchOneInto(Integer.class);
        if (count == null) {
            count = 0;
        }
        return new Response(count, "Ok", 200);
    }

    @Override
    public Response readNotification(int notificationId) {
        int res = context
                .update(USER_NOTIFICATION)
                .set(USER_NOTIFICATION.READ, true)
                .where(USER_NOTIFICATION.USER_NOTIFICATION_ID.eq(notificationId))
                .execute();
        if (res < 1) {
            return new Response(null, "Notification not found", 404);
        }
        return new Response(null, "Ok", 200);
    }

    @Override
    public Response<List<UserNotification>> getNotificationsForCustomer(int customerId, int offset) {
        List<UserNotification> userNotification = context
                .selectFrom(USER_NOTIFICATION)
                .where(USER_NOTIFICATION.CUSTOMER_ID.eq(customerId))
                .orderBy(USER_NOTIFICATION.LAST_UPDATE.desc())
                .offset(offset)
                .limit(5)
                .fetchInto(UserNotification.class);
//        if (userNotification.isEmpty()) {
//            return new Response(null, "User notification not found", 404);
//        }
        return new Response(userNotification, "Ok", 200);
    }
    public Response<UserNotification> insertNotification(UserNotification userNotification) {
        UserNotification userNotificat = context
                .insertInto(USER_NOTIFICATION, USER_NOTIFICATION.CUSTOMER_ID, USER_NOTIFICATION.NOTIFICATION_TEXT, USER_NOTIFICATION.TITLE, USER_NOTIFICATION.READ)
                .values(userNotification.getCustomerId(), userNotification.getNotificationText(), userNotification.getTitle(), false)
                .returning()
                .fetchOne()
                .into(UserNotification.class);
        //rabbitTemplate.convertAndSend(RabbitUtil.rentalRequestResponseTopicName, "notification-rr.", userNotificat);

        if (userNotificat == null) {
            return new Response(null, "Notification for customer not found", 404);
        } else {
            return new Response(userNotificat, "Ok", 200);
        }
    }

    @Override
    public Response readAllNotifications(Integer customerId) {
        context
                .update(USER_NOTIFICATION)
                .set(USER_NOTIFICATION.READ, true)
                .where(USER_NOTIFICATION.CUSTOMER_ID.eq(customerId))
                .execute();
        return new Response(null, "Ok", 200);
    }

}
