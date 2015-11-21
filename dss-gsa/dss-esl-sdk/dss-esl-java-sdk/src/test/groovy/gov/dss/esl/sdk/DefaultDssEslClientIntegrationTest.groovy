package gov.dss.esl.sdk

import com.silanis.esl.sdk.DocumentPackage
import com.silanis.esl.sdk.PackageId

import static com.silanis.esl.sdk.builder.DocumentBuilder.newDocumentWithName
import static com.silanis.esl.sdk.builder.PackageBuilder.newPackageNamed
import static com.silanis.esl.sdk.builder.SignatureBuilder.signatureFor
import static com.silanis.esl.sdk.builder.SignerBuilder.newSignerWithEmail

/**
 *
 *
 *
 * Created by Carvel on 11/21/15 @ 4:03 PM.
 *
 */
class DefaultDssEslClientIntegrationTest extends GroovyTestCase {
    def apiKey = "bFJCTGZQYlgyZ0lZOnIyeXQ2V1c5WGRFNg=="
    def apiUrl = "https://sandbox.e-signlive.com/api"

    void testBasicPackageCreation() {
        //--- set up test data
        DefaultDssEslClient defaultDssEslClient = new DefaultDssEslClient(apiKey, apiUrl);

        DocumentPackage documentPackage = newPackageNamed("DSS SDK TEST PACKAGE A")
                .withSigner(newSignerWithEmail("dss.gsa.developer@gmail.com")
                .withCustomId("customId")
                .withFirstName("signerFirstName")
                .withLastName("signerLastName"))
                .withSigner(newSignerWithEmail("dssdeveloper.ch@gmail.com")
                .withFirstName("yourFirstName")
                .withLastName("yourLastName"))
                .withDocument(newDocumentWithName("sampleAgreement")
                .fromFile("/tmp/sampleAgreement.txt")
                .withSignature(signatureFor("dss.gsa.developer@gmail.com")
                .onPage(0)
                .atPosition(175, 165))
                .withSignature(signatureFor("dssdeveloper.ch@gmail.com")
                .onPage(0)
                .atPosition(550, 165)))
                .build();

        //--- exercise methods

        // Issue the request to the e-SignLive server to create the DocumentPackage
        PackageId packageId = defaultDssEslClient.createPackage(documentPackage);

        // Send the package to be signed by the participants
        defaultDssEslClient.sendPackage(packageId);

        //--- make assertions
    }


}
