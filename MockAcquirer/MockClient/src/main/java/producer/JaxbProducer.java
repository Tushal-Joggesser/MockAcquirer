/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package producer;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import request.MockRequest;
import response.MockResponse;

/**
 *
 * @author tusha
 */
@ApplicationScoped
public class JaxbProducer {

    private JAXBContext jaxbContext;

    public void initialize(@Observes @Initialized(ApplicationScoped.class) Object init) throws JAXBException {
        try {
            jaxbContext = JAXBContext.newInstance(MockRequest.class, MockResponse.class);
        } catch (JAXBException ex) {

        }
    }

    public JAXBContext getJaxbContext() {
        return jaxbContext;
    }

}
