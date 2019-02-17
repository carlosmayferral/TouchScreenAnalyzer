package touchscreenAnalyzer;


import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The responsibility of the file reader factory is to return a type of file reader depending on a provided filename.
 * @author Carlos May
 *
 */
class FileReaderFactoryTest {
	
	FileReaderFactory factory;
	
	@BeforeEach
	void setUp() throws Exception {
		factory = FileReaderFactory.getInstance();
	}

	@Test
	public void factoryReturnsNullWhenFileNameIsUnknown() {
		Assert.assertNull(factory.generateReader("file1.cmn"));
	}
	
	@Test
	public void factoryReturnsCsvReaderOnCsvName() {
		Assert.assertTrue(factory.generateReader("file1.csv").getClass().equals(new CsvReader().getClass()));
	}
	
	@Test
	public void factoryReturnsXmlReaderOnXmlName() {
		Assert.assertTrue(factory.generateReader("file1.xml").getClass().equals(new XmlReader().getClass()));
	}

}
