import studiplayer.basic.WavParamReader;

public class WavFile extends SampledFile {
	
	public WavFile() {
		super();
	}
	
	public WavFile(String path) {
		super(path);
		readAndSetDurationFromFile();
		
	}
	
	public void readAndSetDurationFromFile() {
		 WavParamReader.readParams(getPathname());
		 
		 float frameRate = WavParamReader.getFrameRate();
	     long numberOfFrames = WavParamReader.getNumberOfFrames();
	        

	     setDuration(computeDuration(numberOfFrames, frameRate));
	}
	
	@Override
	public String toString() {
		String baseString = super.toString();
		String formattedDuration = formatDuration();
		
		return baseString + " - " + formattedDuration;
	}
	
	public static long computeDuration(long numberOfFrames, float frameRate) {
		return (long)((numberOfFrames * 1_000_000L) / frameRate);
	}


}
