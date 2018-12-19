package touchscreenAnalyzer;

import java.io.File;

import dataModels.SessionInfo;

interface IExpressReader {

	public SessionInfo readFile(File file);

}
