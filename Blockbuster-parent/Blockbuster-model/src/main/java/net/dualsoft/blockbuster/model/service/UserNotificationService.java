/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.service;

import java.util.List;
import net.dualsoft.blockbuster.db.jooq.tables.pojos.UserNotification;

import net.dualsoft.blockbuster.model.helper.pojos.Response;

/**
 *
 * @author teodora
 */
public interface UserNotificationService {

    public Response<UserNotification> getUserNotification(int id);

    public Response<List<UserNotification>> getUsersNotification();

    public Response<List<UserNotification>> getReadUsersNotification(int id);

    public Response<List<UserNotification>> getUneadUsersNotification(int id);

    public Response<List<UserNotification>> getNotificationForCustomer(int id);

    public Response<List<UserNotification>> setNotificationToRead(int id);
    
    public Response<Integer> unreadNotificationsCount(int id);
    
    public Response readNotification(int notificationId);
    
    public Response<List<UserNotification>> getNotificationsForCustomer(int customerid, int offset);

    public Response<UserNotification> insertNotification(UserNotification userNotification);
    
    public Response readAllNotifications(Integer customerId);
}
