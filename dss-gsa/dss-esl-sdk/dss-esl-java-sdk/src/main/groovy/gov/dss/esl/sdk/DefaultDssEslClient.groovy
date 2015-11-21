package gov.dss.esl.sdk

import com.silanis.esl.sdk.*
import com.silanis.esl.sdk.service.*

/**
 *
 * Default implementation of the DssEslClient interface that simply delegates its operations to the
 * com.silanis.esl.sdk.EslClient implementation. This layer of abstraction enables us to * exercise
 * our code base without the requirement of a live eSignLive API endpoint.
 *
 * Created by Carvel on 11/21/15 @ 2:41 PM.
 *
 */
class DefaultDssEslClient implements DssEslClient {
    /**
     * The EslClient delegate
     */
    final EslClient delegate

    /**
     * The constructor of the DefaultDssEslClient class
     * @param apiKey the api key token
     * @param baseURL the E-SignLive base url
     */
    DefaultDssEslClient(String apiKey, String baseUrl) {
        delegate = new EslClient(apiKey, baseUrl)
    }

    /**
     * The constructor of the DefaultDssEslClient class
     * @param apiKey the api key token
     * @param baseUrl the E-SignLive base url
     * @param webpageUrl the E-SignLive web page url
     */
    DefaultDssEslClient(String apiKey, String baseUrl, String webpageUrl) {
        delegate = new EslClient(apiKey, baseUrl, webpageUrl)
    }


    DefaultDssEslClient(String apiKey, String baseUrl, boolean allowAllSSLCertificates) {
        delegate = new EslClient(apiKey, baseUrl, allowAllSSLCertificates)
    }

    DefaultDssEslClient(String apiKey, String baseUrl, ProxyConfiguration proxyConfiguration) {
        delegate = new EslClient(apiKey, baseUrl, proxyConfiguration)
    }

    DefaultDssEslClient(String apiKey, String baseUrl, boolean allowAllSSLCertificates, ProxyConfiguration proxyConfiguration) {
        delegate = new EslClient(apiKey, baseUrl, allowAllSSLCertificates, proxyConfiguration)
    }

    /**
     * Gets the package service
     * @return the package service
     */
    @Override
    PackageService getPackageService() {
        delegate.packageService
    }

    /**
     * Gets the report service
     * @return the report service
     */
    @Override
    ReportService getReportService() {
        delegate.reportService
    }

    @Override
    SessionService getSessionService() {
        delegate.sessionService
    }

    /**
     * Facilitates access to the service that provides a summary of all the document fields and their values
     *
     * @return the field summary service
     */
    @Override
    FieldSummaryService getFieldSummaryService() {
        delegate.fieldSummaryService
    }

    @Override
    AuditService getAuditService() {
        delegate.auditService
    }

    /**
     * Facilitates access to the service that could be used to register for event notifications
     *
     * @return the event notification service
     */
    @Override
    EventNotificationService getEventNotificationService() {
        delegate.eventNotificationService
    }

    /**
     * Facilitates access to the service that creates authentication tokens
     *
     * @return the authentication token service
     */
    @Override
    AuthenticationTokensService getAuthenticationTokensService() {
        delegate.authenticationTokensService
    }

    /**
     * Facilitates access to the service that could be used to add custom field
     *
     * @return the custom field service
     */
    @Override
    CustomFieldService getCustomFieldService() {
        delegate.customFieldService
    }

    /**
     * <p>Creates the package.</p>
     * <p>This basically does the followings:</p>
     * <p> - converts the document package to JSON format</p>
     * <p> - makes an eSL REST call to actually create the package. Is is using as payload the above generated JSON content.
     *
     * @param documentPackage the document package
     * @return the package ID
     */
    @Override
    PackageId createPackage(DocumentPackage documentPackage) {
        delegate.createPackage(documentPackage)
    }

    /**
     * <p>Update the package, when the package status is only Draft</p>
     *
     * @param packageId
     * @param documentPackage the document package
     */
    @Override
    void updatePackage(PackageId packageId, DocumentPackage documentPackage) {
        delegate.updatePackage(packageId, documentPackage)
    }

    /**
     * <p>Change the package's status from SENT to DRAFT.</p>
     *
     * @param packageId
     */
    @Override
    void changePackageStatusToDraft(PackageId packageId) {
        delegate.changePackageStatusToDraft(packageId)
    }

    /**
     * Creates the package in one step
     *
     * WARNING: DOES NOT WORK WHEN SENDER HAS A SIGNATURE
     *
     * @param documentPackage the document package
     * @return the package ID
     */
    @Override
    PackageId createPackageOneStep(DocumentPackage documentPackage) {
        delegate.createPackageOneStep(documentPackage)
    }

    /**
     * <p>Creates a new packages, and immediately sends it to be signed.</p>
     *
     * @param documentPackage the document package to be created and signed
     * @return the packageId for the newly created package.
     */
    @Override
    PackageId createAndSendPackage(DocumentPackage documentPackage) {
        delegate.createAndSendPackage(documentPackage)
    }

    /**
     * Creates a package based on an existent template
     *
     * @param packageId the package ID used as template for the new package
     * @param documentPackage the document package
     * @return the package ID
     */
    @Override
    PackageId createPackageFromTemplate(PackageId packageId, DocumentPackage documentPackage) {
        delegate.createPackageFromTemplate(packageId, documentPackage)
    }

    /**
     * <p>It does the followings for the package ID specified as argument:</p>
     * <p> - activates the package</p>
     * <p> - send emails to signers and the package owner</p>
     * <p> - sends notifications (if any)</p>
     *
     * @param id the package ID
     */
    @Override
    void sendPackage(PackageId id) {
        delegate.sendPackage(id)
    }

    /**
     * Retrieves a summary for all the document fields of the package identified by its packageId
     * @param packageId the package ID
     * @return a list of field summarys
     */
    @Override
    List<FieldSummary> getFieldValues(PackageId packageId) {
        delegate.getFieldValues(packageId)
    }

    /**
     * @param packageId The document package identifier
     * @return the document package with the given packageId
     */
    @Override
    DocumentPackage getPackage(PackageId packageId) {
        delegate.getPackage(packageId)
    }

    /**
     * Downloads a document that belongs to a package
     * @param packageId the package ID
     * @param documentId the document ID
     * @return the content of the document
     */
    @Override
    byte[] downloadDocument(PackageId packageId, String documentId) {
        delegate.downloadDocument(packageId, documentId)
    }

    /**
     * Downloads an original document that belongs to a package.
     * @param packageId the package ID
     * @param documentId the document ID
     * @return the content of the original document
     */
    @Override
    byte[] downloadOriginalDocument(PackageId packageId, String documentId) {
        delegate.downloadDocument(packageId, documentId)
    }

    /**
     * Downloads the evidence summary for a package
     * @param packageId the package ID
     * @return the content of the evidence summary
     */
    @Override
    byte[] downloadEvidenceSummary(PackageId packageId) {
        delegate.downloadEvidenceSummary(packageId)
    }

    /**
     * Downloads the zipped documents of a package
     * @param packageId the package ID
     * @return the zipped documents
     */
    @Override
    byte[] downloadZippedDocuments(PackageId packageId) {
        delegate.downloadZippedDocuments(packageId)
    }

    @Override
    SigningStatus getSigningStatus(PackageId packageId, SignerId signerId, DocumentId documentId) {
        delegate.getSigningStatus(packageId, signerId, documentId)
    }

    @Override
    Document uploadDocument(String fileName, byte[] fileContent, Document document, DocumentPackage documentPackage) {
        delegate.uploadDocument(fileName, fileContent, document, documentPackage)
    }

    @Override
    Document uploadDocument(Document document, DocumentPackage documentPackage) {
        delegate.uploadDocument(document, documentPackage)
    }

    @Override
    void uploadAttachment(PackageId packageId, String attachmentId, String filename, byte[] fileContent, String signerId) {
        delegate(packageId, attachmentId, filename, signerId)
    }

    @Override
    GroupService getGroupService() {
        delegate.groupService
    }

    @Override
    AccountService getAccountService() {
        delegate.accountService
    }

    @Override
    ApprovalService getApprovalService() {
        delegate.approvalService
    }

    @Override
    ReminderService getReminderService() {
        delegate.reminderService
    }

    @Override
    TemplateService getTemplateService() {
        delegate.templateService
    }

    @Override
    AttachmentRequirementService getAttachmentRequirementService() {
        delegate.attachmentRequirementService
    }

    @Override
    LayoutService getLayoutService() {
        delegate.layoutService
    }

    @Override
    QRCodeService getQrCodeService() {
        delegate.qrCodeService
    }

    @Override
    SystemService getSystemService() {
        delegate.systemService
    }
}
