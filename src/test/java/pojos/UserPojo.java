package pojos;

public class UserPojo {
    private String app_id;
    private String organization_id;
    private String subscription_id;
    private String savedUserId;
    private String savedGroupId;
    private String savedGroup_type_id;

    public String getSavedGroup_type_id() {
        return savedGroup_type_id;
    }

    public void setSavedGroup_type_id(String savedGroup_type_id) {
        this.savedGroup_type_id = savedGroup_type_id;
    }

    public String getSavedGroupId() {
        return savedGroupId;
    }

    public void setSavedGroupId(String savedGroupId) {
        this.savedGroupId = savedGroupId;
    }

    // Getters and setters
    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getOrganization_id() {
        return organization_id;
    }

    public void setOrganization_id(String organization_id) {
        this.organization_id = organization_id;
    }

    public String getSubscription_id() {
        return subscription_id;
    }

    public void setSubscription_id(String subscription_id) {
        this.subscription_id = subscription_id;
    }

    public String getSavedUserId() {
        return savedUserId;
    }

    public void setSavedUserId(String savedUserId) {
        this.savedUserId = savedUserId;
    }
}
