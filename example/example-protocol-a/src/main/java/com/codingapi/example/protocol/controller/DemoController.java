package com.codingapi.example.protocol.controller;

import com.codingapi.example.protocol.message.MyTxCreateGroupMsg;
import com.codingapi.txlcn.protocol.Protocoler;
import com.codingapi.txlcn.protocol.message.Connection;
import com.codingapi.txlcn.protocol.ProtocolServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

/**
 * @author lorne
 * @date 2020/3/4
 * @description
 */
@RestController
public class DemoController {

    @Autowired
    private ProtocolServer protocolServer;

    @GetMapping("/connect")
    public int connect(@RequestParam("host") String host,@RequestParam("port") int port){
        protocolServer.connectTo(host, port);
        return 1;
    }

    @GetMapping("/all")
    public Collection<Connection> all(){
        Protocoler protocoler =  protocolServer.getProtocoler();
        return protocoler.getConnections();
    }


    @GetMapping("/send")
    public int send(@RequestParam("uniqueKey")String uniqueKey,@RequestParam("msg")String msg){
        Protocoler protocoler =  protocolServer.getProtocoler();
        protocoler.sendMsg(uniqueKey,new MyTxCreateGroupMsg(msg));
        return 1;
    }


    @GetMapping("/stop")
    public int stop(){
        Protocoler protocoler =  protocolServer.getProtocoler();
        protocoler.leave(new CompletableFuture<>());
        return 1;
    }

}
