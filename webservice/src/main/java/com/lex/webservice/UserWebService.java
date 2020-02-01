package com.lex.webservice;

import javax.jws.HandlerChain;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
@HandlerChain(file = "Handler.xml")
public class UserWebService {
    public @WebResult(name = "User") User testUser(@WebParam(name = "name") String name) {
        return new UserRepository().findUser(name);
    }

    public @WebResult(name = "User") List<User> testUsers(@WebParam(name = "name") String name) {
        String[] array = name.split(",");
        List<User> l = new ArrayList<>();
        for(String a: array){
            l.add(new UserRepository().findUser(a));
        }
        return l;
    }
}
