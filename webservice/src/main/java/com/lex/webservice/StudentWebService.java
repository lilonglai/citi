package com.lex.webservice;

import javax.jws.HandlerChain;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService
@HandlerChain(file = "Handler.xml")
public class StudentWebService {
    public @WebResult(name = "result") String testStudent(@WebParam(name = "student") Student student) {
        return student.getName() + " : " + student.getAge();
    }
}
