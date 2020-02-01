package com.lex.webservice;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.util.Set;

public class HeaderHandlerClient implements SOAPHandler<SOAPMessageContext> {

    @Override
    public boolean handleMessage(SOAPMessageContext context) {

        try {
            /**
             * True表示Out False表示In
             */
            Boolean messageTag = (Boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);

            /**
             *如果是Out
             */
            if (messageTag) {

                SOAPMessage message = context.getMessage();
                SOAPEnvelope envelope = message.getSOAPPart().getEnvelope();
                SOAPHeader header = envelope.getHeader();

                if (header == null) {
                    header = envelope.addHeader();
                }

                /**
                 * 打印整个SOAP消息
                 */
                message.writeTo(System.out);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        System.out.println("fault");
        return false;
    }

    @Override
    public void close(MessageContext context) {
        // TODO Auto-generated method stub

    }

    @Override
    public Set<QName> getHeaders() {
        // TODO Auto-generated method stub
        return null;
    }
}