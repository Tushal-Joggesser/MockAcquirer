/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package response;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tusha
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "response")
public class MockResponse implements Serializable {

    @XmlElement(name = "responseCode")
    private String responseCode;

    @XmlElement(name = "destinationTransactionId")
    private String destinationTransactionId;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getDestinationTransactionId() {
        return destinationTransactionId;
    }

    public void setDestinationTransactionId(String destinationTransactionId) {
        this.destinationTransactionId = destinationTransactionId;
    }

}
