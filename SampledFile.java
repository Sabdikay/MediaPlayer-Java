import studiplayer.basic.BasicPlayer;

public abstract class SampledFile extends AudioFile{
	private long duration;
	
	public SampledFile() {
		super();
	}
	public SampledFile(String path) {
	        super(path);
	}
	
	@Override
	public void play() {
		BasicPlayer.play(getPathname());
	}
	
	@Override
	public void togglePause() {
		BasicPlayer.togglePause();
	}
	
	@Override
	public void stop() {
		BasicPlayer.stop();
	}
	    
	@Override
	 public String formatDuration() {
	  return timeFormatter(getDuration());
	    }
	    
	@Override
    public String formatPosition() {
        return timeFormatter(BasicPlayer.getPosition());
    }
    
 
    public static String timeFormatter(long timeInMicroSeconds) {
    	if (timeInMicroSeconds < 0) {
    		throw new RuntimeException("Negative time values are not allowed");
    	}
    	long totalSec = timeInMicroSeconds/1_000_000;
    	long mins = totalSec / 60;
    	long sec = totalSec % 60;
    	
    	if (mins > 99) {
    		throw new RuntimeException("Time cannot exceed maximum value in mm:ss format");
    	}
    	
    	return String.format("%02d:%02d", mins, sec);
    }
    

    public long getDuration() {
        return duration;
    }
    
    public void setDuration(long duration) {
        this.duration = duration;
    }
    
}
