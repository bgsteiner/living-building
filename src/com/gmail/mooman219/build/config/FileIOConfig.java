package com.gmail.mooman219.build.config;
import java.io.File;


public class FileIOConfig extends FileIO{

    public boolean debug = false;

    public FileIOConfig(String s) {
        super(s);
    }

    public FileIOConfig(File f) {
        super(f);
    }

    public FileIOConfig(String s, boolean b) {
        super(s);
        debug = b;
    }

    public FileIOConfig(File f, boolean b) {
        super(f);
        debug = b;
    }

    /**
     * 
     * Spacial Methods
     * 
     */

    public void setDebugText(boolean newValue){
        debug = newValue;
    }

    /**
     * 
     * Write Methods
     * 
     */

    private String prepComment(String root, int lineNumber){
        String a = "";
        if(lineNumber < 0)
            lineNumber = 0;
        if(getBodyLine(lineNumber) != ""){
            if(debug){
                System.out.println("prepComment: Running + 1 : " + lineNumber);
            }
            for(int b = 0; b < getBodyLine(lineNumber).length(); b++){
                if(getBodyLine(lineNumber).charAt(b) == ' '){
                    a += " ";
                }else{
                    break;
                }
            }
        }else if(getBodyLine(lineNumber - 1) != ""){
            if(debug){
                System.out.println("prepComment: Running -1 : " + (lineNumber - 1));
            }
            for(int b = 0; b < getBodyLine(lineNumber - 1).length(); b++){
                if(getBodyLine(lineNumber - 1).charAt(b) == ' '){
                    a += " ";
                }else{
                    break;
                }
            }
        }else{
            if(debug){
                System.out.println("prepComment: Failed: Lines before and after invalid");
            }
        }
        return a + "# " + root;
    }

    // Boolean

    public void writeComment(String commentText, int lineNumber, boolean matchSpaces){
        if(matchSpaces)
            writeAddBefore(prepComment(commentText, lineNumber), lineNumber);
        else
            writeAddBefore("# " + commentText, lineNumber);
    }

    // String

    public void writeComment(String commentText, String root, boolean matchSpaces){
        int lineNumber = getPropertyLine(root);
        if(matchSpaces)
            writeAddBefore(prepComment(commentText, lineNumber), lineNumber);
        else
            writeAddBefore("# " + commentText, lineNumber);
    }

    //

    public void writeProperty(String root, Object x){
        String[] breakdown = getRootBreakdown(root);
        if(breakdown.length <= 0){
            if(debug){
                System.out.println("writeProperty: False: Size " + breakdown.length);
            }
            return;
        }
        if(getIfPropertyExist(root)){
            if(debug){
                System.out.println("writeProperty: Overwriting: " + root);
            }
            String spaces = "";
            for(int a = 0; a < breakdown.length - 1; a++){
                spaces += "    ";
            }
            if(debug){
                System.out.println("writeProperty:1 '" + spaces + breakdown[breakdown.length - 1] + ": " + String.valueOf(x) + "'");
            }
            writeLine(spaces + breakdown[breakdown.length - 1] + ": " + String.valueOf(x), getPropertyLine(breakdown));
        }else{
            String spaces = "";
            int highestLine = 0;
            for(int a = 0; a < breakdown.length; a++){
                spaces = "";
                String[] tempBreakdown = new String[a + 1];
                for(int c = 0; c < a; c++){
                    spaces += "    ";
                }
                for(int c = 0; c < a + 1; c++){
                    tempBreakdown[c] = breakdown[c];
                }
                if(!getIfPropertyExist(tempBreakdown)){
                    if(debug){
                        System.out.println("writeProperty:2 '" + spaces + breakdown[a] + ": " + "'");
                    }
                    writeAdd(spaces + breakdown[a] + ": ");
                    highestLine = this.getPropertyLine(tempBreakdown);
                }else{
                    highestLine++;
                }
            }
            if(debug){
                System.out.println("writeProperty:3 '" + getBodyLine(highestLine) + String.valueOf(x) + "'");
            }
            writeLine(getBodyLine(highestLine) + String.valueOf(x), highestLine);
        }
    }

    /**
     * 
     * Advanced Get Value Methods
     * 
     */

    public int getHierarchy(String root){
        String[] breakdown = getRootBreakdown(root);
        if(breakdown.length < 1){
            if(debug){
                System.out.println("getHierarchy(String): Null: " + root);
            }
            return 0;
        }
        if(!getIfPropertyExist(breakdown)){
            if(debug){
                System.out.println("getHierarchy(String): Does not exist");
            }
            return 0;
        }
        return breakdown.length - 1;
    }

    public int getHierarchy(String[] breakdown){
        if(breakdown.length < 1){
            if(debug){
                System.out.println("getHierarchy(String[]): Null: " + getRootFromBreakdown(breakdown));
            }
            return 0;
        }
        if(!getIfPropertyExist(breakdown)){
            if(debug){
                System.out.println("getHierarchy(String[]): Does not exist");
            }
            return 0;
        }
        return breakdown.length - 1;
    }

    public int getHierarchy(int lineNumber){
        String a = getBodyLine(lineNumber);
        if(a.length() < 1){
            if(debug){
                System.out.println("getHierarchy(int): Null: " + a);
            }
            return 0;
        }
        if(a.charAt(0) != ' ' || lineNumber <= 0){
            if(debug){
                System.out.println("getHierarchy(int): return 0: a.charAt(0) != ' ' || lineNumber <= 0");
            }
            return 0;
        }
        int spaceCount = 0;
        for(int b = 0; b < a.length(); b++){
            if(a.charAt(b) == ' '){
                spaceCount++;
            }else{
                break;
            }
        }
        int tempSpaceCount = 0;
        int hierarchy = 0;
        for(int b = lineNumber; b >= 0; b--){
            a = getBodyLine(b);
            tempSpaceCount = 0;
            for(int c = 0; c < getBodyLine(b).length(); c++){
                if(a.charAt(c) == ' '){
                    tempSpaceCount++;
                }else{
                    if(a.charAt(c) == '#'){
                        tempSpaceCount = spaceCount;
                    }
                    break;
                }
            }
            if(tempSpaceCount < spaceCount){
                hierarchy++;
                spaceCount = tempSpaceCount;
            }
        }
        return hierarchy;
    }

    public String getRootFromBreakdown(String[] breakdown){
        if(breakdown.length <= 0){
            if(debug){
                System.out.println("getRootFromBreakdown: Null: Size " + breakdown.length);
            }
            return null;
        }
        String a = "";
        for(int b = 0; b < breakdown.length; b++){
            a += breakdown[b];
            if(b < breakdown.length - 1){
                a += ".";
            }
        }
        return a;
    }

    public String[] getRootBreakdown(String root){
        if(root.length() <= 0){
            if(debug){
                System.out.println("getRootBreakdown: Null: Size " + root.length());
            }
            return null;
        }
        return root.split("\\.");
    }

    public int getPropertyLine(String[] breakdown){
        if(breakdown.length <= 0){
            if(debug){
                System.out.println("getPropertyLine: False: Size " + breakdown.length);
            }
            return 0;
        }
        String c = "";
        int d = 0;
        int[] spaceCount = new int[breakdown.length];
        for(int a = 0; a < body.size(); a++){
            c = "";
            for(int b = 0; b < body.get(a).length(); b++){
                if(body.get(a).charAt(b) != ' '){
                    if(body.get(a).charAt(b) == ':'){
                        break;
                    }
                    c = c + body.get(a).charAt(b);
                    if(body.get(a).charAt(b) == '#'){
                        c = "#";
                        break;
                    }
                }else{
                    spaceCount[d]++;
                }
            }
            if(c.equalsIgnoreCase(breakdown[d])){
                if(spaceCount[d] == 4 * d){
                    d++;
                    if(d == breakdown.length)
                        return a;
                }
            }else{
                spaceCount[d] = 0;
            }
        }
        if(debug){
            System.out.println("getPropertyLine: False: No Match");
        }
        return 0;

    }

    public int getPropertyLine(String root){
        return getPropertyLine(getRootBreakdown(root));
    }

    public boolean getIfPropertyExist(String[] breakdown){
        if(breakdown.length <= 0){
            if(debug){
                System.out.println("getIfPropertyExist: False: Size " + breakdown.length);
            }
            return false;
        }
        String c;
        int d = 0;
        int[] spaceCount = new int[breakdown.length];
        for(String a:body){
            c = "";
            for(int b = 0; b < a.length(); b++){
                if(a.charAt(b) != ' '){
                    if(a.charAt(b) == ':'){
                        break;
                    }else{
                        if(a.charAt(b) == '#'){
                            c = "..Null..";
                            break;
                        }else{
                            c = c + a.charAt(b);
                        }
                    }
                }else{
                    spaceCount[d]++;
                }
            }
            if(c.equalsIgnoreCase(breakdown[d])){
                if(spaceCount[d] == 4 * d){
                    d++;
                    if(d == breakdown.length)
                        return true;
                }
            }else{
                spaceCount[d] = 0;
            }
        }
        return false;
    }

    public boolean getIfPropertyExist(String root){
        return getIfPropertyExist(getRootBreakdown(root));
    }

    /**
     * 
     * Normal Get Value Methods
     * 
     */

    /**
     * Get's the float stored on a given line
     * @param root - The root string of the value.
     * @param defaultValue - The integer returned if it fails.
     */

    public float getFloat(String root, float defaultValue){
        if(root.length() < 1){
            if(debug){
                System.out.println("getFloat: False: root.length() < 1 root:'" + root + "'");
            }
            return defaultValue;
        }
        if(!getIfPropertyExist(root)){
            if(debug){
                System.out.println("getFloat: False: !doesPropertyExist(root) root:'" + root + "'");
            }
            return defaultValue;
        }
        String[] breakDown = getRootBreakdown(root);
        String d = "";
        if(breakDown.length > 0){
            String a = getBodyLine(getPropertyLine(breakDown));
            for(int b = 0; b < a.length(); b++){
                if(a.charAt(b) == ':'){
                    for(int c = b + 1; c < a.length(); c++){
                        if(a.charAt(c) != ' '){
                            d += a.charAt(c);
                        }
                    }
                    break;
                }
            }
            try{
                float returnValue = Float.parseFloat(d);
                if(debug){
                    System.out.println("getInt: " + root + ": '" + d + "'  returnValue: " + returnValue);
                }
                return returnValue;
            }catch(Exception e){
                System.out.println("getInt: Format Error root:'" + root + "'");
                return defaultValue;
            }
        }
        if(debug){
            System.out.println("getInt: False: Size " + breakDown.length + " root:'" + root + "'");
        }
        return defaultValue;
    }

    /**
     * Get's the integer stored on a given line
     * @param root - The root string of the value.
     * @param defaultValue - The integer returned if it fails.
     */

    public int getInt(String root, int defaultValue){
        if(root.length() < 1){
            if(debug){
                System.out.println("getInt: False: root.length() < 1 root:'" + root + "'");
            }
            return defaultValue;
        }
        if(!getIfPropertyExist(root)){
            if(debug){
                System.out.println("getInt: False: !doesPropertyExist(root) root:'" + root + "'");
            }
            return defaultValue;
        }
        String[] breakDown = getRootBreakdown(root);
        String d = "";
        if(breakDown.length > 0){
            String a = getBodyLine(getPropertyLine(breakDown));
            for(int b = 0; b < a.length(); b++){
                if(a.charAt(b) == ':'){
                    for(int c = b + 1; c < a.length(); c++){
                        if(a.charAt(c) != ' '){
                            d += a.charAt(c);
                        }
                    }
                    break;
                }
            }
            try{
                int returnValue = Integer.parseInt(d);
                if(debug){
                    System.out.println("getInt: " + root + ": '" + d + "'  returnValue: " + returnValue);
                }
                return returnValue;
            }catch(Exception e){
                System.out.println("getInt: Format Error root:'" + root + "'");
                return defaultValue;
            }
        }
        if(debug){
            System.out.println("getInt: False: Size " + breakDown.length + " root:'" + root + "'");
        }
        return defaultValue;
    }

    /**
     * Get's the boolean stored on a given line
     * @param root - The root string of the value.
     * @param defaultValue - The boolean returned if it fails.
     */

    public boolean getBoolean(String root, boolean defaultValue){
        if(root.length() < 1){
            if(debug){
                System.out.println("getBoolean: False: root.length() < 1 root:'" + root + "'");
            }
            return defaultValue;
        }
        if(!getIfPropertyExist(root)){
            if(debug){
                System.out.println("getBoolean: False: !doesPropertyExist(root) root:'" + root + "'");
            }
            return defaultValue;
        }
        String[] breakDown = getRootBreakdown(root);
        String d = "";
        if(breakDown.length > 0){
            String a = getBodyLine(getPropertyLine(breakDown));
            for(int b = 0; b < a.length(); b++){
                if(a.charAt(b) == ':'){
                    for(int c = b + 1; c < a.length(); c++){
                        if(a.charAt(c) != ' '){
                            d += a.charAt(c);
                        }
                    }
                    break;
                }
            }
            try{
                if(d.length() >= 4){
                    boolean returnValue = Boolean.parseBoolean(d);
                    if(debug){
                        System.out.println("getBoolean: " + root + ": '" + d + "'  returnValue: " + returnValue);
                    }
                    return returnValue;
                }
                if(debug){
                    System.out.println("getBoolean: d is to small root:'" + root + "'");
                }
                return defaultValue;
            }catch(Exception e){
                if(debug){
                    System.out.println("getBoolean: final error root:'" + root + "'");
                }
                return defaultValue;
            }
        }
        else if(debug){
            System.out.println("getBoolean: False: Size " + breakDown.length + " root:'" + root + "'");
        }
        return defaultValue;
    }

    /**
     * Get's the string stored on a given line
     * @param root - The root string of the value.
     * @param defaultValue - The string returned if it fails.
     */

    public String getString(String root, String defaultValue){
        if(root.length() < 1){
            if(debug){
                System.out.println("getString: False: root.length() < 1 root:'" + root + "'");
            }
            return defaultValue;
        }
        if(!getIfPropertyExist(root)){
            if(debug){
                System.out.println("getString: False: !doesPropertyExist(root) root:'" + root + "'");
            }
            return defaultValue;
        }
        String[] breakDown = getRootBreakdown(root);
        String d = "";
        if(breakDown.length > 0){
            String a = getBodyLine(getPropertyLine(breakDown));
            for(int b = 0; b < a.length(); b++){
                if(a.charAt(b) == ':'){
                    if(a.charAt(b + 1) == ' ')
                        b++;
                    for(int c = b + 1; c < a.length(); c++){
                        d += a.charAt(c);
                    }
                    break;
                }
            }
            try{
                String returnValue = d;
                if(debug){
                    System.out.println("getString: " + root + ": '" + d + "'  returnValue: " + returnValue);
                }
                return returnValue;
            }catch(Exception e){
                return defaultValue;
            }
        }
        if(debug){
            System.out.println("getString: False: Size " + breakDown.length + " root:'" + root + "'");
        }
        return defaultValue;
    }

    //

    /**
     * Get's the float stored on a given line
     * Automatically uses '0.0f' as the return value if it fails to get the variable
     * @param root - The root string of the value.
     */

    public float getFloat(String root){
        return getFloat(root, 0.0f);
    }

    /**
     * Get's the integer stored on a given line
     * Automatically uses '0' as the return value if it fails to get the variable
     * @param root - The root string of the value.
     */

    public int getInt(String root){
        return getInt(root, 0);
    }

    /**
     * Get's the boolean stored on a given line
     * Automatically uses 'false' as the return value if it fails to get the variable
     * @param root - The root string of the value.
     */

    public boolean getBoolean(String root){
        return getBoolean(root, false);
    }

    /**
     * Get's the string stored on a given line
     * Automatically uses 'Error' as the return value if it fails to get the variable
     * @param root - The root string of the value.
     */

    public String getString(String root){
        return getString(root, "");
    }
}