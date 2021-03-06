package com.colin.dao;

import com.colin.util.ConnectionUtils;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Recv {
    //RabbitMQ的队列名称
    private final static String QUEUE_NAME = "simple_queue";

    public static void main(String[] args) throws IOException, TimeoutException {
        //使用工具类，获取RabbitMQ的连接
        Connection connection = ConnectionUtils.getConnection();
        //使用连接创建通道
        Channel channel = connection.createChannel();
        //使用通道声明一个队列，参数分别为：队列名称，是否持久化，是否为排他队列，是否自动删除，其他属性
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //定义队列的消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            //覆写该方法，该方法类似于监听器，当队列中有消息的时候，就会被调用执行
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //byte[] body参数即为消息体，当该消息体被使用，即认为该消息已被消费
                System.out.println(new String(body));
            }
        };
        //监听队列，参数分别为：队列名称，是否自动确认消息已被消费，队列消费者
        channel.basicConsume(QUEUE_NAME, true, consumer);
    }
}
