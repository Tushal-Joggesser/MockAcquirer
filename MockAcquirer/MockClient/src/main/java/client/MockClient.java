/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package client;

import enums.EnumResponseCodes.ResponseCodes;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.logging.Level;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import static javax.xml.bind.Marshaller.JAXB_ENCODING;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import producer.JaxbProducer;
import request.MockRequest;
import response.MockResponse;

/**
 *
 * @author tusha
 */
@WebServlet(name = "MockClient", urlPatterns = {"/MockClient"})
@ApplicationScoped
public class MockClient extends HttpServlet {

//URL : http://localhost:8080/api/MockClient
    /* Request Details
<request>
<cardNumber></cardNumber>
<amount></amount>
<cardHolderName></cardHolderName>
<expiryMonth></expiryMonth>
<expiryYear></expiryYear>
<cvv></cvv>
</request>
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MockClient.class);

    @Inject
    private JaxbProducer jaxbContext;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String responseDetails)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println(responseDetails);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response, "IN GET");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    @Consumes(MediaType.APPLICATION_XML)
    @Produces(MediaType.APPLICATION_XML)
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Incoming POST request");
// READ DATA
        MockRequest mockRequest = readData(request);
        MockResponse mockResponse = null;
        ResponseCodes rc = null;
        String finalResponse = null;
        if (mockRequest != null) {
            mockResponse = new MockResponse();
            mockResponse.setDestinationTransactionId(UUID.randomUUID().toString());
            switch (mockRequest.getCardNumber()) {
// All approved cards
//5123450000000008
//2223000000000007
//4508750015741019
                case "5123450000000008":
                case "2223000000000007":
                case "4508750015741019":
                    rc = ResponseCodes.SUCCESSFULL;
                    break;
//INSUFFICIENT_FUNDS
//5111111111111118
//2223000000000023
                case "5111111111111118":
                case "2223000000000023":
                    rc = ResponseCodes.INSUFFICIENT_FUNDS;
                    break;
//TIMED_OUT
//4012000033330026
//5111111114411118
                case "4012000033330026":
                case "5111111114411118":
                    rc = ResponseCodes.TIMEOUT;

                    break;
                default:
//                    DECLINED
                    rc = ResponseCodes.TIMEOUT;
                    break;
            }
            mockResponse.setResponseCode(rc.getResponseCodes());
            finalResponse = marshallResponse(mockResponse);
        } else {
            finalResponse = "Invalid Requests";
        }

        processRequest(request, response, finalResponse);
    }

    private String readRequest(HttpServletRequest request) {
        BufferedReader reader;
        try {
            reader = request.getReader();
            String line;
            StringBuilder requestBody = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                requestBody.append(line);
            }
            return requestBody.toString();
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(MockClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private MockRequest readData(HttpServletRequest request) {
        String requestBody = readRequest(request);
        LOGGER.info("Raw Request: " + requestBody);
        try {
            Unmarshaller unmarshaller = jaxbContext.getJaxbContext().createUnmarshaller();
            StreamSource xmlSource = new StreamSource(new StringReader(requestBody));
            return (MockRequest) unmarshaller.unmarshal(xmlSource);
        } catch (JAXBException ex) {
            java.util.logging.Logger.getLogger(MockClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private String marshallResponse(MockResponse mockResponse) {
        String xmlResponse = null;
        try {
            StringWriter writer = new StringWriter();
            Marshaller marshaller = jaxbContext.getJaxbContext().createMarshaller();
            marshaller.setProperty(JAXB_ENCODING, StandardCharsets.UTF_8.toString());
            marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
            marshaller.marshal(mockResponse, writer);
            xmlResponse = writer.toString();
            LOGGER.info("Raw Response: " + xmlResponse);
        } catch (JAXBException ex) {
            LOGGER.error("" + ex);
        }
        return xmlResponse;
    }
}
