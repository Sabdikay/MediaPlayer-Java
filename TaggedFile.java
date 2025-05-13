import java.util.Map;
import studiplayer.basic.TagReader;

public class TaggedFile extends SampledFile {
	private String album;
	
	public TaggedFile() {
		super();
	}
	
	public TaggedFile(String path) {
		super(path);
		readAndStoreTags();
	}
	
	public String getAlbum() {
		if (album == null) {
            return "";
        }
		return album.trim();
	}
	
	public void readAndStoreTags() {
        Map<String,Object> tags = TagReader.readTags(getPathname());
		
        Object o;
		
	    o = tags.get("title");
		if(o!=null) {
			setTitle(((String) o).trim());
		}
		
		o = tags.get("author");
		if(o!=null) {
			setAuthor(((String) o).trim());
		}
		
		o = tags.get("album");
		if(o!=null) {
			album = (((String) o).trim()); 
		}
		
		o = tags.get("duration");
		if(o!=null) {
		    if (o instanceof Long) {
		    	setDuration((Long) o);
		    }
		}
			
		
	}
	
	@Override
	public String toString() {
		String baseString = super.toString();
		String formattedDuration = formatDuration();
		
		baseString = baseString.trim().replaceAll("\\s+", " ");
		
		if (getAlbum() != null && !getAlbum().isEmpty()) {
			return baseString + " - " + getAlbum().trim() + " - " + formattedDuration;
		}else {
			return baseString + " - " + formattedDuration;
		}
	}


}
