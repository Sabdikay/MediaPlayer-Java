
public class AudioFile {
	private String pathname;
	private String filename;
	private String author;
	private String title;
	//Constructors
	public AudioFile() {
		
	}
    public AudioFile(String path) {
		parsePathname(path);
		parseFilename(filename);
	}
    
    private boolean isWindows() {
    	return System.getProperty("os.name").toLowerCase().contains("win");
    }
    
    /**
     * Normalizes path according to current OS and stores the result in "pathname".
     * Identifies the filename in path and stores in "filename".
     * 
     * @param path
     */
    
    public void parsePathname(String path) {
    	if (!isWindows()) {
    		if(path.length() >= 2) {
    			char first = path.charAt(0);
    			char second = path.charAt(1);
    			
    			if((first >= 'a' && first <= 'z'|| first >= 'A' && first <= 'Z') && second == ':') {
        			pathname = "/" + first +path.substring(2);
    			}else {
    				pathname = path;
    			}
    			
    		}else {
    			pathname = path;
    		}
    	}else {
    		pathname = path;
    	}
    	
    	while (pathname.indexOf("\\\\") >= 0) {
    		pathname = pathname.replace("\\\\", "\\");
    	}
    	while (pathname.indexOf("//") >= 0){
    		pathname = pathname.replace("//","/");
    	}
    	char sep = '/';
    	char wrongSep = '\\';
    	if (isWindows()) {
    		sep = '\\';
    		wrongSep = '/';
    	}
    	while(pathname.indexOf(wrongSep)>= 0) {
    		pathname = pathname.replace(wrongSep, sep);
    	}
    	
    	pathname = pathname.trim();
    	int lastSepIndex = pathname.lastIndexOf(sep);
        if (lastSepIndex >= 0 && lastSepIndex < pathname.length() - 1) {
            this.filename = pathname.substring(lastSepIndex + 1);
        } else if (lastSepIndex == pathname.length()-1) {
        	this.filename = "";
        } else {
            this.filename = pathname;
        }
        
        if (this.filename.equals(" -")) {
            this.filename = "-";
        }
        if (this.filename.equals("- ")) {
            this.filename = "-";
        }
    }
    
    /*
     * Splits the given filename into "author" and "title".
     * @param file
     */
    public void parseFilename(String givenFilename) {
    	if (givenFilename == null) return;
    	
    	if (givenFilename.startsWith(".")) {
    	        this.author = "";
    	        this.title = "";  
    	        return;
    	    }
    	if (givenFilename == " ") {
    		this.author = "";
	        this.title = "";  
	        return;
	    }
    	if (givenFilename == " - ") {
    		this.author = "";
	        this.title = "";  
	        return;
	    }
    	String[] parts = givenFilename.split(" - ");
    	if (parts.length == 2) {
    		this.author = parts[0].trim();
        	this.title = parts[1].trim();
        	int dotind = this.title.lastIndexOf(".");
        	if (dotind>0) {
        		this.title = this.title.substring(0,dotind).trim();
    	}
    	}else {
    		this.author = "";
    		this.title= givenFilename;
    		int dotind = this.title.lastIndexOf(".");
    		if (dotind>0) {
        		this.title = this.title.substring(0,dotind);
    	}
        }
    }
    
	public String getPathname() {
		return pathname;
	}

	/*public void setPathname(String pathname) {
		this.pathname = pathname;
	}*/

	public String getFilename() {
		return filename;
	}

	/*public void setFilename(String filename) {
		this.filename = filename;
	}*/

	public String getAuthor() {
		return author;
	}

	/*public void setAuthor(String author) {
		this.author = author;
	}*/

	public String getTitle() {
		return title;
	}

	@Override
	public String toString() {
		if (filename != null && filename.equals(" - ") || title != null && title.equals(" - ")) {
	        return "-";
	    }
	    if (filename != null && filename.startsWith(".") || title != null && title.startsWith(".")) {
	        return "";
	    }
	    if ((title == null || title.isEmpty()) && (author == null || author.isEmpty())) {
	        return "";
	    }
	    if (author == null || author.isEmpty()) {
	        return title;
	    }
	    return author + " - " + title;
	}

	public static void main(String[] args) {
		//Window path:drive + dir path+filename+extension
		//filename:<author> + "-" + ,title.
		AudioFile af = new AudioFile("C:\\media\\Falco - Rock me amadeus.mp3");
		//AudioFile af = new AudioFile("/part1/mymusic/ -");;
		System.out.println(af.getPathname());
		System.out.println(af.getFilename());
		System.out.println(af.getAuthor());
		System.out.println(af.getTitle());
		AudioFile af1 = new AudioFile(" - ");
	    System.out.println("Pathname: '" + af1.getPathname() + "'");
	    System.out.println("Filename: '" + af1.getFilename() + "'");
	    System.out.println("Author: '" + af1.getAuthor() + "'");
	    System.out.println("Title: '" + af1.getTitle() + "'");
	    System.out.println("ToString: '" + af1.toString() + "'");

		

	}



}
