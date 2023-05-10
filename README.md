# MockAcquirer
Mock Acquirer Responses For Test Cases (Credit Card)

This is a sample HTTP Servlet which will be used to simulate test cases as per the following sample cards below.

# POST URL
http://localhost:8080/api/MockClient

# Sample XML Request
<request>
<cardNumber></cardNumber>
<amount></amount>
<cardHolderName></cardHolderName>
<expiryMonth></expiryMonth>
<expiryYear></expiryYear>
<cvv></cvv>
</request>

# Approved cards
5123450000000008
2223000000000007
4508750015741019

# INSUFFICIENT_FUNDS
5111111111111118
2223000000000023

# TIMED_OUT
4012000033330026
5111111114411118

# Installation
1. Start your wildfly server / any jboss server
2. Clean and build the project OR use the war file (MockClient.war) in the target folder.
3. Note: if you mvn clean install, the pom file already has execution command to deploy direct to JBOSS (* The server needs to listen on the required port - check pom.xml file).
4. HTTP Header should be accept : application/xml & content-type: application/xml
5. Sample VM 
![image](https://github.com/Tushal-Joggesser/MockAcquirer/assets/63640322/4b1603ed-d1e6-44df-a220-76a3c5692cc0)
