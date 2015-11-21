package gov.dss.esl.sdk;

import com.silanis.esl.sdk.*;
import com.silanis.esl.sdk.service.*;

import java.util.List;

/**
 * A facade interface for the com.silanis.esl.sdk.EslClient
 * <p/>
 * Created by Carvel on 11/21/15 @ 2:27 PM.
 */
public interface DssEslClient {
    /**
     * Gets the package service
     *
     * @return the package service
     */
    public PackageService getPackageService();

    /**
     * Gets the report service
     *
     * @return the report service
     */
    public ReportService getReportService();


    public SessionService getSessionService();

    /**
     * Facilitates access to the service that provides a summary of all the document fields and their values
     *
     * @return the field summary service
     */
    public FieldSummaryService getFieldSummaryService();

    public AuditService getAuditService();

    /**
     * Facilitates access to the service that could be used to register for event notifications
     *
     * @return the event notification service
     */
    public EventNotificationService getEventNotificationService();

    /**
     * Facilitates access to the service that creates authentication tokens
     *
     * @return the authentication token service
     */
    public AuthenticationTokensService getAuthenticationTokensService();


    /**
     * Facilitates access to the service that could be used to add custom field
     *
     * @return the custom field service
     */
    public CustomFieldService getCustomFieldService();

    /**
     * <p>Creates the package.</p>
     * <p>This basically does the followings:</p>
     * <p> - converts the document package to JSON format</p>
     * <p> - makes an eSL REST call to actually create the package. Is is using as payload the above generated JSON content.
     *
     * @param documentPackage the document package
     * @return the package ID
     */
    public PackageId createPackage(DocumentPackage documentPackage);

    /**
     * <p>Update the package, when the package status is only Draft</p>
     *
     * @param packageId
     * @param documentPackage the document package
     */
    public void updatePackage(PackageId packageId, DocumentPackage documentPackage);

    /**
     * <p>Change the package's status from SENT to DRAFT.</p>
     *
     * @param packageId
     */
    public void changePackageStatusToDraft(PackageId packageId);

    /**
     * Creates the package in one step
     * <p/>
     * WARNING: DOES NOT WORK WHEN SENDER HAS A SIGNATURE
     *
     * @param documentPackage the document package
     * @return the package ID
     */

    public PackageId createPackageOneStep(DocumentPackage documentPackage);


    /**
     * <p>Creates a new packages, and immediately sends it to be signed.</p>
     *
     * @param documentPackage the document package to be created and signed
     * @return the packageId for the newly created package.
     */
    public PackageId createAndSendPackage(DocumentPackage documentPackage);

    /**
     * Creates a package based on an existent template
     *
     * @param packageId       the package ID used as template for the new package
     * @param documentPackage the document package
     * @return the package ID
     */
    public PackageId createPackageFromTemplate(PackageId packageId, DocumentPackage documentPackage);

    /**
     * <p>It does the followings for the package ID specified as argument:</p>
     * <p> - activates the package</p>
     * <p> - send emails to signers and the package owner</p>
     * <p> - sends notifications (if any)</p>
     *
     * @param id the package ID
     */
    public void sendPackage(PackageId id);

    /**
     * Retrieves a summary for all the document fields of the package identified by its packageId
     *
     * @param packageId the package ID
     * @return a list of field summarys
     */
    public List<FieldSummary> getFieldValues(PackageId packageId);


    /**
     * @param packageId The document package identifier
     * @return the document package with the given packageId
     */
    public DocumentPackage getPackage(PackageId packageId);

    /**
     * Downloads a document that belongs to a package
     *
     * @param packageId  the package ID
     * @param documentId the document ID
     * @return the content of the document
     */
    public byte[] downloadDocument(PackageId packageId, String documentId);

    /**
     * Downloads an original document that belongs to a package.
     *
     * @param packageId  the package ID
     * @param documentId the document ID
     * @return the content of the original document
     */
    public byte[] downloadOriginalDocument(PackageId packageId, String documentId);

    /**
     * Downloads the evidence summary for a package
     *
     * @param packageId the package ID
     * @return the content of the evidence summary
     */
    public byte[] downloadEvidenceSummary(PackageId packageId);

    /**
     * Downloads the zipped documents of a package
     *
     * @param packageId the package ID
     * @return the zipped documents
     */
    public byte[] downloadZippedDocuments(PackageId packageId);

    public SigningStatus getSigningStatus(PackageId packageId, SignerId signerId, DocumentId documentId);

    public Document uploadDocument(String fileName, byte[] fileContent, Document document, DocumentPackage documentPackage);

    public Document uploadDocument(Document document, DocumentPackage documentPackage);

    public void uploadAttachment(PackageId packageId, String attachmentId, String filename, byte[] fileContent, String signerId);

    public GroupService getGroupService();

    public AccountService getAccountService();

    public ApprovalService getApprovalService();

    public ReminderService getReminderService();

    public TemplateService getTemplateService();

    public AttachmentRequirementService getAttachmentRequirementService();

    public LayoutService getLayoutService();

    public QRCodeService getQrCodeService();

    public SystemService getSystemService();
}
