package Classes;

public class ManageMembersTM {
    private String memberId;
    private String memberName;
    private String memberAddress;
    private String contactNo;

    public ManageMembersTM(String memberId, String memberName, String memberAddress, String contactNo) {
        this.setMemberId(memberId);
        this.setMemberName(memberName);
        this.setMemberAddress(memberAddress);
        this.setContactNo(contactNo);
    }

    public ManageMembersTM() {
    }


    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getMemberAddress() {
        return memberAddress;
    }

    public void setMemberAddress(String memberAddress) {
        this.memberAddress = memberAddress;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public String toString() {
        return "ManageMembersTM{" +
                "memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberAddress='" + memberAddress + '\'' +
                ", contactNo='" + contactNo + '\'' +
                '}';
    }
}