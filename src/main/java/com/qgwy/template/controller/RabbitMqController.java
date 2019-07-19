package com.qgwy.template.controller;


import com.qgwy.template.bean.User2;
import com.qgwy.template.mapper.User2Repository;
import com.qgwy.template.util.R;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("mq")
public class RabbitMqController {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private User2Repository user2Repository;

    @RequestMapping("/send")
    @ResponseBody
    public R sendMsg(String msg){
        if (!StringUtils.isEmpty(msg)) {
        rabbitTemplate.convertAndSend("amq.direct","qgwy",msg);
            return R.ok().put("data","消息发送成功，消息内容为："+msg);
        }
        User2 one = user2Repository.getOne(1L);
        User2 target = new User2().setName("wawa").setId(10).setPhone("18874544568").setSex(1);
        rabbitTemplate.convertAndSend("amq.direct","qgwy",target);
        return R.ok().put("data","消息发送成功，消息内容为："+target);
    }

    @RequestMapping("/receive")
    @ResponseBody
    public R receiveMsg(){
        Message message = rabbitTemplate.receive("qgwy_news");
        if (!StringUtils.isEmpty(message)) {
            return R.ok().put("data","接收到的消息内容为："+message);
        }
        return R.ok().put("data","当前未接收到任何消息");
    }
}
