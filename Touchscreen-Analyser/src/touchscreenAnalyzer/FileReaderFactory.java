package touchscreenAnalyzer;

public class FileReaderFactory {
	
	private static FileReaderFactory singletonInstance = null;

	public static FileReaderFactory getInstance() {
		if (singletonInstance == null) {
			singletonInstance = new FileReaderFactory();
		}
		return singletonInstance;
	}

	public IFileReader generateReader(String name) {
		if (name.contains(".csv")) {
			return new CsvReader();
		}
		else if (name.contains(".xml")) {
			return new XmlReader();
		}
		else {
			System.out.println("Format of file " + name + " not recognized, skipping...");
			return null;
		}
	}
	
	

}
