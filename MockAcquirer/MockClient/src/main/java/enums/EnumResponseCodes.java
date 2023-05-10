/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package enums;

/**
 *
 * @author tusha
 */
public class EnumResponseCodes {

    public enum ResponseCodes {
        SUCCESSFULL("000"),
        TIMEOUT("603"),
        INSUFFICIENT_FUNDS("551"),
        DECLINE("506");
        private String responseCodes;

        private ResponseCodes(String responseCodes) {
            this.responseCodes = responseCodes;
        }

        public String getResponseCodes() {
            return responseCodes;
        }

        public void setResponseCodes(String responseCodes) {
            this.responseCodes = responseCodes;
        }

    }

}
