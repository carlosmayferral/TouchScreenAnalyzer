package touchscreenAnalyzer;



import java.io.File;
import java.util.HashMap;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import dataModels.Identifier;
import dataModels.MetaData;


class MetaFileReaderTest {
	
	static MetaFileReader reader;
	
	static HashMap<Identifier, MetaData> groupInfoMap;
	
	static File testFile0;
	static final String testFile0Address = "testfiles/MetaFileReader/metafiletest0.meta";
	static Identifier testFile0Id0;
	static final String testFile0Header = "Genotype,Experimenter,Facepaint,";
	static final String testFile0AnimalId0 = "11";
	static final String testFile0GroupId0 = "5";
	static final String testFile0Metadata0 = "Nl3ki,Carlos,Crazy,";
	
	static File testFile1;
	static final String testFile1Header = "Genotype,Experimenta!,Facepaint,";
	static final String testFile1Address = "testfiles/MetaFileReader/metafiletest1.meta";
	static Identifier testFile1Id0;
	static final String testFile1AnimalId0 = "11G";
	static final String testFile1GroupId0 = "Ethanol";
	static final String testFile1Metadata0 = "WT,Christina,Blue,";
	static Identifier testFile1Id2;
	static final String testFile1AnimalId2 = "Cohort1I2";
	static final String testFile1GroupId2 = null;
	
	@BeforeEach
	void setUp() throws Exception {
		reader = new MetaFileReader();
		groupInfoMap = new HashMap<>();
		testFile0 =new File(testFile0Address);
		testFile0Id0 = new Identifier(testFile0AnimalId0,testFile0GroupId0);
		testFile1 = new File (testFile1Address);
		testFile1Id0 = new Identifier(testFile1AnimalId0,testFile1GroupId0);
		testFile1Id2 = new Identifier(testFile1AnimalId2, testFile1GroupId2	);
	}

	//Size of Mapping
	
	@Test
	void readFileReturnsOneMappingForTestFile0() {
		reader.read(testFile0, groupInfoMap);
		Assert.assertEquals(1, groupInfoMap.size());
	}
	
	@Test
	void readFileReturnsThreeMappingsForTestFile1() {
		reader.read(testFile1, groupInfoMap);
		Assert.assertEquals(3, groupInfoMap.size());
	}
	
	//Key is correct
	
	@Test
	void readFileHasMappingForCorrectKeyInTestFile0() {
		reader.read(testFile0, groupInfoMap);
		Assert.assertNotNull(groupInfoMap.get(testFile0Id0));
	}
	
	@Test
	void readFileHasMappingForCorrectKeyInTestFile1() {
		reader.read(testFile1, groupInfoMap);
		Assert.assertNotNull(groupInfoMap.get(new Identifier(testFile1AnimalId0,testFile1GroupId0)));
	}
	
	@Test
	void readFileHasMappingForAnimalWithoutGroup(){
		reader.read(testFile1, groupInfoMap);
		Assert.assertNotNull(groupInfoMap.get(testFile1Id2));
	}
	
	//Headers are correct
	
	@Test
	void readFileReturnsCorrectHeadersForTestFile0() {
		reader.read(testFile0, groupInfoMap);
		Assert.assertEquals(MetaData.getHeaderString(), testFile0Header);
	}
	
	@Test
	void readFileReturnsCorrectHeadersForTestFile1() {
		reader.read(testFile1, groupInfoMap);
		Assert.assertEquals(MetaData.getHeaderString(), testFile1Header);
	}
	
	//Values are correct
	
	@Test
	void readFileMapsValueCorrectlyInTestFile0() {
		reader.read(testFile0, groupInfoMap);
		Assert.assertEquals(groupInfoMap.get(testFile0Id0).getDataString(), testFile0Metadata0);
	}
	
	@Test
	void readFileMapsValueCorrectlyInTestFile1() {
		reader.read(testFile1, groupInfoMap);
		Assert.assertEquals(groupInfoMap.get(testFile1Id0).getDataString(), testFile1Metadata0);
	}

}
