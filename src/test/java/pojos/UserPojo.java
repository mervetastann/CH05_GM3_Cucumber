package pojos;

public class UserPojo {
    private String app_id;
    private String organization_id;
    private String subscription_id;
    private String savedUserId;
    private String GroupId;
    private String Group_type_id;

    public String getGroup_type_id() {
        return Group_type_id;
    }

    public void setGroup_type_id(String group_type_id) {
        this.Group_type_id = group_type_id;
    }

    public String getGroupId() {
        return GroupId;
    }

    public void setGroupId(String groupId) {
        this.GroupId = groupId;
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
