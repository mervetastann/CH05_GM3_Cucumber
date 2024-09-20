package pojos;

public class UserPojo {
    private String app_id;
    private String organization_id;
    private String subscription_id;
    private String savedUserId;
    private String groupId;
    private String group_type_id;
    private String sub_status_id;


    public String getSub_status_id() {
        return sub_status_id;
    }

    public void setSub_status_id(String sub_status_id) {
        this.sub_status_id = sub_status_id;
    }


    public String getGroup_type_id() {
        return group_type_id;
    }

    public void setGroup_type_id(String group_type_id) {
        this.group_type_id = group_type_id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
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
