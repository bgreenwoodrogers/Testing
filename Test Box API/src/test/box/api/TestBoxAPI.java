/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test.box.api;

import java.util.*;

import com.box.sdk.BoxAPIConnection;
import com.box.sdk.BoxFolder;
import com.box.sdk.BoxItem;
import com.box.sdk.BoxFile;

public class TestBoxAPI {
    
    public static void main(String[] args) {
        
        
        BoxAPIConnection api = new BoxAPIConnection("58lSES6ZSkhiFIKASeSWkBkZFJKBaSIE");
        BoxFolder rootFolder = BoxFolder.getRootFolder(api);
        
        //System.out.println(ReturnItemID(rootFolder, "New Folder"));
        
        BoxFolder SubFolder = new BoxFolder(api, ReturnItemID(rootFolder, "New Folder"));
        
        //CopyFile(api,SubFolder,ReturnItemID(rootFolder, "Testing.txt"),"Copy.txt");
        
        //BoxFile File = new BoxFile(api,ReturnItemID(rootFolder,"Testing.txt"));
        //File.move(SubFolder);
        
        BoxFile File = new BoxFile(api,ReturnItemID(SubFolder,"Testing.txt"));
        File.move(rootFolder);
        
        /*
        for (BoxItem.Info itemInfo : rootFolder) {
            if (itemInfo.getName().equals("CopiedFile.txt")){
                System.out.println("Found " + itemInfo.getName());
                System.out.println(ReturnItemID(rootFolder, "CopiedFile.txt"));
            }
            else {
                System.out.println("Not found " + itemInfo.getName());
            }
        }
        */
        
        //DeleteFile(api, ReturnItemID(rootFolder, "CopiedFile.txt"));
        
        //BoxFolder.Info Info = rootFolder.getInfo();
        
        //System.out.println(Info);
        
        /*
        Iterable<BoxItem.Info> results = rootFolder.search("Testing.txt");
        for (BoxItem.Info result : results) {
            System.out.format("[%s] %s\n", result.getID(), result.getName());
            System.out.println(result.getID());
            BoxFile fileToCopy = new BoxFile(api, result.getID());
            BoxFile.Info copiedFileInfo = fileToCopy.copy(rootFolder, "CopiedFile.txt");
        }
        */
        
        /*
        for (BoxItem.Info itemInfo : rootFolder) {
            if (itemInfo.getName() == "Testing.txt"){
                System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
                System.out.println("success");
            }
            else {
                System.out.format("[%s] %s\n", itemInfo.getID(), itemInfo.getName());
                System.out.println("failure");
            }
        }
        */
        /*
        BoxFile fileToCopy = new BoxFile(api, "69207322353");
        BoxFile.Info copiedFileInfo = fileToCopy.copy(rootFolder, "CopiedFile.txt");
        */
    }
    
    static String ReturnItemID(BoxFolder Folder, String ItemName) { 
        String ID = "Item Not Found";
        for (BoxItem.Info itemInfo : Folder) {
            if (itemInfo.getName().equals(ItemName)) {
                ID = itemInfo.getID();
            }
        }
        return ID;
    }
    
    static void MoveItem(BoxItem Item, BoxFolder MoveToFolder) {
        
        
    }
    
    static void CopyFile(BoxAPIConnection api, BoxFolder CopyToFolder, String FileID, String NewFileName) {
        if (SearchFolderForItem(CopyToFolder, NewFileName)) {
            System.out.println("Copy file name already exists");
        }
        else {
            BoxFile file = new BoxFile(api, FileID);
            BoxFile.Info copiedFileInfo = file.copy(CopyToFolder, NewFileName);
        }
    }
    
    static void DeleteFile(BoxAPIConnection api, String FileID) {
        try {
            BoxFile file = new BoxFile(api, FileID);
            file.delete();
        }
        
        catch(Exception ex) {
            System.out.println("File to delete not found");
        }
        
    }
    
    static boolean SearchFolderForItem(BoxFolder Folder, String FileName) {
        boolean result = false;
        for (BoxItem.Info itemInfo : Folder) {
            if (itemInfo.getName().equals(FileName)) {
                result = true;
            }
        }
        return result;
    }
    
    static boolean BoxSearchFolderForFile(BoxFolder Folder, String FileName) {
        //Function to search all sub folders of input folder for File with 
        //name Filename. Uses Box search function, does NOT search for exact 
        //match 
        
        Iterable<BoxItem.Info> FileSearchResults = Folder.search(FileName);
        
        /*
        for (BoxItem.Info result : FileSearchResults) {
            System.out.format("[%s] %s\n", result.getID(), result.getName());
        }
        */
        
        Iterator SearchIterator = FileSearchResults.iterator();
        return SearchIterator.hasNext();
    }
    
    //static void CopyFile()
}
