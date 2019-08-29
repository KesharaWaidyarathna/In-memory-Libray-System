package Classes;

import Classes.ManageMembersTM;

import java.security.PublicKey;
import java.util.ArrayList;

public class LibraryDB {

    public static ArrayList<ManageMembersTM>members=new ArrayList<>();
    public  static ArrayList<ManageBooksTM>books=new ArrayList<>();
    public static ArrayList<IssueBooksTM>issueBooks=new ArrayList<>();
    public static ArrayList<ReturnBooksTM>returnBooks=new ArrayList<>();

    static {

        members.add(new ManageMembersTM("M001","kasun","matara","0771324126"));

        books.add(new ManageBooksTM("b001","Harry poter","nelson","Available"));


    }


}
