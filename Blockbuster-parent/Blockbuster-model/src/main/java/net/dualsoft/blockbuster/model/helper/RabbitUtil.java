/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.dualsoft.blockbuster.model.helper;


public class RabbitUtil {

    public static final String rentalRequestTopicName = "auth-rr-exchange";

    public static final String rentalRequestQueueName = "auth-rr";

    public static final String rentalRequestCancelTopicName = "auth-rr-cancel-exchange";

    public static final String rentalRequestCancelQueueName = "auth-rr-cancel";

    public static final String rentalRequestResponseTopicName = "core-rr-response-exchange";

    public static final String rentalRequestResponseQueueName = "core-rr-response";

    public static final String notificationQueueName = "notification-rr";
}
