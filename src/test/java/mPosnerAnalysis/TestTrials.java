package mPosnerAnalysis;

public class TestTrials {

    public static String distractedTrial1 ="192.722,1,Condition Event,Next trial,,10,0,,,,,,,,,,\n" +
            "192.722,16,Variable Event,ITI_Timer,,10,1,Value,1,,,,,,,,\n" +
            "192.722,16,Variable Event,ITI_Timer,,10,1,Value,0,,,,,,,,\n" +
            "192.722,16,Variable Event,SkipTrial_inc,,10,1,Value,0,,,,,,,,\n" +
            "192.722,16,Variable Event,First_Analysis,,10,1,Value,1,,,,,,,,\n" +
            "192.722,16,Variable Event,Hold_Timer,,10,1,Value,0.27,,,,,,,,\n" +
            "192.722,16,Variable Event,Hold_Timer,,10,1,Value,0,,,,,,,,\n" +
            "192.722,16,Variable Event,zzRT_Timer,,10,1,Value,0.09,,,,,,,,\n" +
            "192.722,16,Variable Event,zzRT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "192.722,16,Variable Event,zzMT_Timer,,10,1,Value,2.5,,,,,,,,\n" +
            "192.722,16,Variable Event,zzMT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "192.722,21,Group Change Event,Group Change,,10,1,New Group,27,,,,,,,,\n" +
            "192.722,1,Condition Event,Change Hold Time,,27,0,,,,,,,,,,\n" +
            "192.722,16,Variable Event,Hold_Time_Adjuster,,27,1,Value,0.186,,,,,,,,\n" +
            "192.722,21,Group Change Event,Group Change,,27,1,New Group,3,,,,,,,,\n" +
            "192.722,1,Condition Event,After 10 trials,,3,0,,,,,,,,,,\n" +
            "192.722,16,Variable Event,_Trial_Counter,,3,1,Value,23,,,,,,,,\n" +
            "192.722,16,Variable Event,Correct_Percent,,3,1,Value,9,,,,,,,,\n" +
            "192.722,16,Variable Event,Correct_Percent,,3,1,Value,0.391,,,,,,,,\n" +
            "192.722,16,Variable Event,Correct_Percent,,3,1,Value,39.13,,,,,,,,\n" +
            "192.722,16,Variable Event,Accuracy_NoAE,,3,1,Value,9,,,,,,,,\n" +
            "192.722,16,Variable Event,Accuracy_NoAE,,3,1,Value,0.5,,,,,,,,\n" +
            "192.722,16,Variable Event,Accuracy_NoAE,,3,1,Value,50,,,,,,,,\n" +
            "192.722,16,Variable Event,Validity,,3,1,Value,9,,,,,,,,\n" +
            "192.722,19,List Change - Next Value,Validity_List,,3,2,Index,58,Value,0,,,,,,\n" +
            "192.722,21,Group Change Event,Group Change,,3,1,New Group,17,,,,,,,,\n" +
            "192.722,1,Condition Event,Select Alerting Trials,,17,0,,,,,,,,,,\n" +
            "192.722,16,Variable Event,Trial_Type,,17,1,Value,913,,,,,,,,\n" +
            "192.722,19,List Change - Next Value,Alerting_Trials,,17,2,Index,8,Value,931,,,,,,\n" +
            "192.722,16,Variable Event,Distractor,,17,1,Value,1,,,,,,,,\n" +
            "192.722,19,List Change - Next Value,Alerting_Distractor,,17,2,Index,1,Value,0,,,,,,\n" +
            "192.722,21,Group Change Event,Group Change,,17,1,New Group,4,,,,,,,,\n" +
            "192.722,1,Condition Event,Select Trial Type,,4,0,,,,,,,,,,\n" +
            "192.722,32,Whisker - Display Image,mANT,Image,4,2,Position,4,CentralStimulus,0,,,,,,\n" +
            "192.722,16,Variable Event,aTrial_Set,,4,1,Value,913,,,,,,,,\n" +
            "192.722,16,Variable Event,Hold_Time,,4,1,Value,0.186,,,,,,,,\n" +
            "192.722,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\n" +
            "192.722,1,Condition Event,Trial set 913,,5,0,,,,,,,,,,\n" +
            "192.722,16,Variable Event,Target_Position,,5,1,Value,1,,,,,,,,\n" +
            "192.722,16,Variable Event,Incorrect_Position,,5,1,Value,3,,,,,,,,\n" +
            "192.722,21,Group Change Event,Group Change,,5,1,New Group,15,,,,,,,,\n" +
            "193.177,39,Input Transition Off Event,FIRBeam #1,,15,0,,,,,,,,,,\n" +
            "193.193,30,Touch Down Event,mANT,,15,4,Position,4,CentralStimulus,0,CoordX,53,CoordY,86,,\n" +
            "193.193,1,Condition Event,Display Alerting Cue,,15,0,,,,,,,,,,\n" +
            "193.193,32,Whisker - Display Image,mANT,Image,15,2,Position,4,dim,3,,,,,,\n" +
            "193.193,32,Whisker - Display Image,mANT,Cue_Images,15,2,Position,5,Frame,2,,,,,,\n" +
            "193.193,29,Timer Event,Hold_Timer,,15,1,Value,0,,,,,,,,\n" +
            "193.193,21,Group Change Event,Group Change,,15,1,New Group,37,,,,,,,,\n" +
            "193.196,38,Input Transition On Event,FIRBeam #1,,37,0,,,,,,,,,,\n" +
            "193.343,1,Condition Event,Hold_Cue,,37,0,,,,,,,,,,\n" +
            "193.343,32,Whisker - Display Image,mANT,Background,37,2,Position,5,Image 1,0,,,,,,\n" +
            "193.343,32,Whisker - Display Image,mANT,Image,37,2,Position,4,dim,3,,,,,,\n" +
            "193.343,21,Group Change Event,Group Change,,37,1,New Group,16,,,,,,,,\n" +
            "193.378,1,Condition Event,Hold_CTI_Distractor,,16,0,,,,,,,,,,\n" +
            "193.378,32,Whisker - Display Image,mANT,Image,16,2,Position,1,Target,1,,,,,,\n" +
            "193.378,32,Whisker - Display Image,mANT,Image,16,2,Position,3,Distractor,2,,,,,,\n" +
            "193.378,16,Variable Event,Hold_Failure_Counter,,16,1,Value,0,,,,,,,,\n" +
            "193.378,29,Timer Event,zzRT_Timer,,16,1,Value,0,,,,,,,,\n" +
            "193.378,29,Timer Event,Target_Timer,,16,1,Value,0,,,,,,,,\n" +
            "193.378,21,Group Change Event,Group Change,,16,1,New Group,7,,,,,,,,\n" +
            "193.407,39,Input Transition Off Event,FIRBeam #1,,7,0,,,,,,,,,,\n" +
            "193.454,38,Input Transition On Event,FIRBeam #1,,7,0,,,,,,,,,,\n" +
            "193.482,31,Touch Up Event,mANT,,7,4,Position,4,dim,3,CoordX,49,CoordY,77,,\n" +
            "193.483,1,Condition Event,Leave_During Target,,7,0,,,,,,,,,,\n" +
            "193.483,32,Whisker - Display Image,mANT,Background,7,2,Position,4,Image 1,0,,,,,,\n" +
            "193.483,16,Variable Event,zzRT_Timer,,7,1,Value,0.104,,,,,,,,\n" +
            "193.483,16,Variable Event,Hold_Timer,,7,1,Value,0.29,,,,,,,,\n" +
            "193.483,29,Timer Event,zzMT_Timer,,7,1,Value,0,,,,,,,,\n" +
            "193.483,21,Group Change Event,Group Change,,7,1,New Group,25,,,,,,,,\n" +
            "194.378,1,Condition Event,Target off,,25,0,,,,,,,,,,\n" +
            "194.378,16,Variable Event,Target_Timer,,25,1,Value,1,,,,,,,,\n" +
            "194.378,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "194.378,32,Whisker - Display Image,mANT,Background,25,2,Position,1,Image 1,0,,,,,,\n" +
            "194.378,32,Whisker - Display Image,mANT,Background,25,2,Position,3,Image 1,0,,,,,,\n" +
            "194.378,61,Group Maintain Event,Group Change,,25,1,New Group,25,,,,,,,,\n" +
            "195.983,1,Condition Event,Omission_Leaving During Target,,25,0,,,,,,,,,,\n" +
            "195.983,16,Variable Event,Timeout,,25,1,Value,1,,,,,,,,\n" +
            "195.983,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "195.983,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "195.983,16,Variable Event,zzMT_Timer,,25,1,Value,2.5,,,,,,,,\n" +
            "195.983,21,Group Change Event,Group Change,,25,1,New Group,33,,,,,,,,\n" +
            "195.983,1,Condition Event,Increment_OmissionError,,33,0,,,,,,,,,,\n" +
            "195.983,16,Variable Event,zz_Omission_Counter,,33,1,Value,5,,,,,,,,\n" +
            "195.983,16,Variable Event,aCounter_NoAE,,33,1,Value,19,,,,,,,,\n" +
            "195.983,16,Variable Event,Change_Hold_Time,,33,1,Value,1,,,,,,,,\n" +
            "195.983,21,Group Change Event,Group Change,,33,1,New Group,11,,,,,,,,\n" +
            "195.983,1,Condition Event,Time Out,,11,0,,,,,,,,,,\n" +
            "195.983,32,Whisker - Display Image,mANT,Background,11,2,Position,4,Image 1,0,,,,,,\n" +
            "195.983,32,Whisker - Display Image,mANT,Background,11,2,Position,1,Image 1,0,,,,,,\n" +
            "195.983,1,Condition Event,TO Houselight On,,11,0,,,,,,,,,,\n" +
            "195.983,3,Output On Event,HouseLight #1,,11,0,,,,,,,,,,\n" +
            "195.983,16,Variable Event,Timeout,,11,1,Value,0,,,,,,,,\n" +
            "195.983,29,Timer Event,Time_Out_Timer,,11,1,Value,0,,,,,,,,\n" +
            "195.983,61,Group Maintain Event,Group Change,,11,1,New Group,11,,,,,,,,\n" +
            "195.983,1,Condition Event,TO Houselight On,,11,0,,,,,,,,,,\n" +
            "195.983,3,Output On Event,HouseLight #1,,11,0,,,,,,,,,,\n" +
            "195.983,21,Group Change Event,Group Change,,11,1,New Group,13,,,,,,,,\n" +
            "196.983,1,Condition Event,Time Out End,,13,0,,,,,,,,,,\n" +
            "196.983,16,Variable Event,Time_Out_Timer,,13,1,Value,1,,,,,,,,\n" +
            "196.983,16,Variable Event,Time_Out_Timer,,13,1,Value,0,,,,,,,,\n" +
            "196.983,29,Timer Event,ITI_Timer,,13,1,Value,0,,,,,,,,\n" +
            "196.983,21,Group Change Event,Group Change,,13,1,New Group,14,,,,,,,,\n" +
            "196.983,1,Condition Event,Reset Houselight off,,14,0,,,,,,,,,,\n" +
            "196.983,4,Output Off Event,HouseLight #1,,14,0,,,,,,,,,,\n" +
            "196.983,21,Group Change Event,Group Change,,14,1,New Group,10,,,,,,,,\n" +
            "197.983,1,Condition Event,Next trial,,10,0,,,,,,,,,,\n";

    public static String distractedTrial2 = "209.119,16,Variable Event,ITI_Timer,,10,1,Value,1,,,,,,,,\n" +
            "209.119,16,Variable Event,ITI_Timer,,10,1,Value,0,,,,,,,,\n" +
            "209.119,16,Variable Event,SkipTrial_inc,,10,1,Value,0,,,,,,,,\n" +
            "209.119,16,Variable Event,First_Analysis,,10,1,Value,1,,,,,,,,\n" +
            "209.119,16,Variable Event,Hold_Timer,,10,1,Value,0.213,,,,,,,,\n" +
            "209.119,16,Variable Event,Hold_Timer,,10,1,Value,0,,,,,,,,\n" +
            "209.119,16,Variable Event,zzRT_Timer,,10,1,Value,0.018,,,,,,,,\n" +
            "209.119,16,Variable Event,zzRT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "209.119,16,Variable Event,zzMT_Timer,,10,1,Value,0.099,,,,,,,,\n" +
            "209.119,16,Variable Event,zzMT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "209.119,21,Group Change Event,Group Change,,10,1,New Group,27,,,,,,,,\n" +
            "209.119,1,Condition Event,Change Hold Time,,27,0,,,,,,,,,,\n" +
            "209.119,16,Variable Event,Hold_Time_Adjuster,,27,1,Value,0.18,,,,,,,,\n" +
            "209.119,21,Group Change Event,Group Change,,27,1,New Group,3,,,,,,,,\n" +
            "209.119,1,Condition Event,After 10 trials,,3,0,,,,,,,,,,\n" +
            "209.119,16,Variable Event,_Trial_Counter,,3,1,Value,28,,,,,,,,\n" +
            "209.119,16,Variable Event,Correct_Percent,,3,1,Value,10,,,,,,,,\n" +
            "209.119,16,Variable Event,Correct_Percent,,3,1,Value,0.357,,,,,,,,\n" +
            "209.119,16,Variable Event,Correct_Percent,,3,1,Value,35.714,,,,,,,,\n" +
            "209.119,16,Variable Event,Accuracy_NoAE,,3,1,Value,10,,,,,,,,\n" +
            "209.119,16,Variable Event,Accuracy_NoAE,,3,1,Value,0.5,,,,,,,,\n" +
            "209.119,16,Variable Event,Accuracy_NoAE,,3,1,Value,50,,,,,,,,\n" +
            "209.119,16,Variable Event,Validity,,3,1,Value,0,,,,,,,,\n" +
            "209.119,19,List Change - Next Value,Validity_List,,3,2,Index,60,Value,0,,,,,,\n" +
            "209.119,21,Group Change Event,Group Change,,3,1,New Group,17,,,,,,,,\n" +
            "209.119,1,Condition Event,Select Valid Trials,,17,0,,,,,,,,,,\n" +
            "209.119,16,Variable Event,Trial_Type,,17,1,Value,4531,,,,,,,,\n" +
            "209.119,19,List Change - Next Value,Valid_Trials,,17,2,Index,8,Value,14513,,,,,,\n" +
            "209.119,16,Variable Event,Distractor,,17,1,Value,1,,,,,,,,\n" +
            "209.119,19,List Change - Next Value,Valid_Distractor,,17,2,Index,3,Value,0,,,,,,\n" +
            "209.119,21,Group Change Event,Group Change,,17,1,New Group,4,,,,,,,,\n" +
            "209.119,1,Condition Event,Select Trial Type,,4,0,,,,,,,,,,\n" +
            "209.119,32,Whisker - Display Image,mANT,Image,4,2,Position,4,CentralStimulus,0,,,,,,\n" +
            "209.119,16,Variable Event,aTrial_Set,,4,1,Value,4531,,,,,,,,\n" +
            "209.119,16,Variable Event,Hold_Time,,4,1,Value,0.18,,,,,,,,\n" +
            "209.119,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\n" +
            "209.119,1,Condition Event,Trial set 4531,,5,0,,,,,,,,,,\n" +
            "209.119,16,Variable Event,Cue,,5,1,Value,1,,,,,,,,\n" +
            "209.119,16,Variable Event,Target_Position,,5,1,Value,3,,,,,,,,\n" +
            "209.119,16,Variable Event,Incorrect_Position,,5,1,Value,1,,,,,,,,\n" +
            "209.119,21,Group Change Event,Group Change,,5,1,New Group,15,,,,,,,,\n" +
            "209.597,39,Input Transition Off Event,BIRBeam #1,,15,0,,,,,,,,,,\n" +
            "209.968,30,Touch Down Event,mANT,,15,4,Position,5,Image 1,0,CoordX,34,CoordY,142,,\n" +
            "210.084,31,Touch Up Event,mANT,,15,4,Position,5,Image 1,0,CoordX,35,CoordY,142,,\n" +
            "210.362,30,Touch Down Event,mANT,,15,4,Position,4,CentralStimulus,0,CoordX,41,CoordY,23,,\n" +
            "210.363,1,Condition Event,Display Cue,,15,0,,,,,,,,,,\n" +
            "210.363,32,Whisker - Display Image,mANT,Cue_Images,15,2,Position,4,45,0,,,,,,\n" +
            "210.363,29,Timer Event,Hold_Timer,,15,1,Value,0,,,,,,,,\n" +
            "210.363,21,Group Change Event,Group Change,,15,1,New Group,37,,,,,,,,\n" +
            "210.513,1,Condition Event,Hold_Cue,,37,0,,,,,,,,,,\n" +
            "210.513,32,Whisker - Display Image,mANT,Background,37,2,Position,5,Image 1,0,,,,,,\n" +
            "210.513,32,Whisker - Display Image,mANT,Image,37,2,Position,4,dim,3,,,,,,\n" +
            "210.513,21,Group Change Event,Group Change,,37,1,New Group,16,,,,,,,,\n" +
            "210.543,1,Condition Event,Hold_CTI_Distractor,,16,0,,,,,,,,,,\n" +
            "210.543,32,Whisker - Display Image,mANT,Image,16,2,Position,3,Target,1,,,,,,\n" +
            "210.543,32,Whisker - Display Image,mANT,Image,16,2,Position,1,Distractor,2,,,,,,\n" +
            "210.543,16,Variable Event,Hold_Failure_Counter,,16,1,Value,0,,,,,,,,\n" +
            "210.543,29,Timer Event,zzRT_Timer,,16,1,Value,0,,,,,,,,\n" +
            "210.543,29,Timer Event,Target_Timer,,16,1,Value,0,,,,,,,,\n" +
            "210.543,21,Group Change Event,Group Change,,16,1,New Group,7,,,,,,,,\n" +
            "210.573,31,Touch Up Event,mANT,,7,4,Position,4,dim,3,CoordX,41,CoordY,23,,\n" +
            "210.574,1,Condition Event,Leave_During Target,,7,0,,,,,,,,,,\n" +
            "210.574,32,Whisker - Display Image,mANT,Background,7,2,Position,4,Image 1,0,,,,,,\n" +
            "210.574,16,Variable Event,zzRT_Timer,,7,1,Value,0.031,,,,,,,,\n" +
            "210.574,16,Variable Event,Hold_Timer,,7,1,Value,0.211,,,,,,,,\n" +
            "210.574,29,Timer Event,zzMT_Timer,,7,1,Value,0,,,,,,,,\n" +
            "210.574,21,Group Change Event,Group Change,,7,1,New Group,25,,,,,,,,\n" +
            "210.613,30,Touch Down Event,mANT,,25,4,Position,1,Distractor,2,CoordX,156,CoordY,200,,\n" +
            "210.614,1,Condition Event,Incorrect_Leaving During Target,,25,0,,,,,,,,,,\n" +
            "210.614,32,Whisker - Display Image,mANT,Background,25,2,Position,3,Image 1,0,,,,,,\n" +
            "210.614,32,Whisker - Display Image,mANT,Background,25,2,Position,1,Image 1,0,,,,,,\n" +
            "210.614,16,Variable Event,Timeout,,25,1,Value,1,,,,,,,,\n" +
            "210.614,16,Variable Event,Target_Timer,,25,1,Value,0.071,,,,,,,,\n" +
            "210.614,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "210.614,16,Variable Event,zzMT_Timer,,25,1,Value,0.04,,,,,,,,\n" +
            "210.614,21,Group Change Event,Group Change,,25,1,New Group,32,,,,,,,,\n" +
            "210.614,1,Condition Event,Increment_IncorrectError,,32,0,,,,,,,,,,\n" +
            "210.614,16,Variable Event,zz_Incorrect_Counter,,32,1,Value,6,,,,,,,,\n" +
            "210.614,16,Variable Event,aCounter_NoAE,,32,1,Value,21,,,,,,,,\n" +
            "210.614,16,Variable Event,Change_Hold_Time,,32,1,Value,1,,,,,,,,\n" +
            "210.614,21,Group Change Event,Group Change,,32,1,New Group,11,,,,,,,,\n" +
            "210.614,1,Condition Event,Time Out,,11,0,,,,,,,,,,\n" +
            "210.614,32,Whisker - Display Image,mANT,Background,11,2,Position,4,Image 1,0,,,,,,\n" +
            "210.614,32,Whisker - Display Image,mANT,Background,11,2,Position,3,Image 1,0,,,,,,\n" +
            "210.614,1,Condition Event,TO Houselight On,,11,0,,,,,,,,,,\n" +
            "210.614,3,Output On Event,HouseLight #1,,11,0,,,,,,,,,,\n" +
            "210.614,16,Variable Event,Timeout,,11,1,Value,0,,,,,,,,\n" +
            "210.614,29,Timer Event,Time_Out_Timer,,11,1,Value,0,,,,,,,,\n" +
            "210.614,61,Group Maintain Event,Group Change,,11,1,New Group,11,,,,,,,,\n" +
            "210.614,1,Condition Event,TO Houselight On,,11,0,,,,,,,,,,\n" +
            "210.614,3,Output On Event,HouseLight #1,,11,0,,,,,,,,,,\n" +
            "210.614,21,Group Change Event,Group Change,,11,1,New Group,13,,,,,,,,\n" +
            "210.784,31,Touch Up Event,mANT,,13,4,Position,1,Image 1,0,CoordX,174,CoordY,182,,\n" +
            "210.982,38,Input Transition On Event,FIRBeam #1,,13,0,,,,,,,,,,\n" +
            "211.056,39,Input Transition Off Event,FIRBeam #1,,13,0,,,,,,,,,,\n" +
            "211.424,38,Input Transition On Event,BIRBeam #1,,13,0,,,,,,,,,,\n" +
            "211.485,39,Input Transition Off Event,BIRBeam #1,,13,0,,,,,,,,,,\n" +
            "211.614,1,Condition Event,Time Out End,,13,0,,,,,,,,,,\n" +
            "211.614,16,Variable Event,Time_Out_Timer,,13,1,Value,1,,,,,,,,\n" +
            "211.614,16,Variable Event,Time_Out_Timer,,13,1,Value,0,,,,,,,,\n" +
            "211.614,29,Timer Event,ITI_Timer,,13,1,Value,0,,,,,,,,\n" +
            "211.614,21,Group Change Event,Group Change,,13,1,New Group,14,,,,,,,,\n" +
            "211.614,1,Condition Event,Reset Houselight off,,14,0,,,,,,,,,,\n" +
            "211.614,4,Output Off Event,HouseLight #1,,14,0,,,,,,,,,,\n" +
            "211.614,21,Group Change Event,Group Change,,14,1,New Group,10,,,,,,,,\n" +
            "212.614,1,Condition Event,Next trial,,10,0,,,,,,,,,,\n";

    public static String distractedTrial3 = "237.653,16,Variable Event,ITI_Timer,,10,1,Value,1,,,,,,,,\n" +
            "237.653,16,Variable Event,ITI_Timer,,10,1,Value,0,,,,,,,,\n" +
            "237.653,16,Variable Event,SkipTrial_inc,,10,1,Value,0,,,,,,,,\n" +
            "237.653,16,Variable Event,First_Analysis,,10,1,Value,1,,,,,,,,\n" +
            "237.653,16,Variable Event,Hold_Timer,,10,1,Value,0.289,,,,,,,,\n" +
            "237.653,16,Variable Event,Hold_Timer,,10,1,Value,0,,,,,,,,\n" +
            "237.653,16,Variable Event,zzRT_Timer,,10,1,Value,0.104,,,,,,,,\n" +
            "237.653,16,Variable Event,zzRT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "237.653,16,Variable Event,zzMT_Timer,,10,1,Value,1.936,,,,,,,,\n" +
            "237.653,16,Variable Event,zzMT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "237.653,21,Group Change Event,Group Change,,10,1,New Group,27,,,,,,,,\n" +
            "237.653,1,Condition Event,Change Hold Time,,27,0,,,,,,,,,,\n" +
            "237.653,16,Variable Event,Hold_Time_Adjuster,,27,1,Value,0.181,,,,,,,,\n" +
            "237.653,21,Group Change Event,Group Change,,27,1,New Group,3,,,,,,,,\n" +
            "237.653,1,Condition Event,After 10 trials,,3,0,,,,,,,,,,\n" +
            "237.653,16,Variable Event,_Trial_Counter,,3,1,Value,34,,,,,,,,\n" +
            "237.653,16,Variable Event,Correct_Percent,,3,1,Value,12,,,,,,,,\n" +
            "237.653,16,Variable Event,Correct_Percent,,3,1,Value,0.353,,,,,,,,\n" +
            "237.653,16,Variable Event,Correct_Percent,,3,1,Value,35.294,,,,,,,,\n" +
            "237.653,16,Variable Event,Accuracy_NoAE,,3,1,Value,12,,,,,,,,\n" +
            "237.653,16,Variable Event,Accuracy_NoAE,,3,1,Value,0.48,,,,,,,,\n" +
            "237.653,16,Variable Event,Accuracy_NoAE,,3,1,Value,48,,,,,,,,\n" +
            "237.653,16,Variable Event,Validity,,3,1,Value,8,,,,,,,,\n" +
            "237.653,19,List Change - Next Value,Validity_List,,3,2,Index,65,Value,0,,,,,,\n" +
            "237.653,21,Group Change Event,Group Change,,3,1,New Group,17,,,,,,,,\n" +
            "237.653,1,Condition Event,Select No Cue Trials,,17,0,,,,,,,,,,\n" +
            "237.653,16,Variable Event,Trial_Type,,17,1,Value,831,,,,,,,,\n" +
            "237.653,19,List Change - Next Value,NoCue_Trials,,17,2,Index,10,Value,831,,,,,,\n" +
            "237.653,16,Variable Event,Distractor,,17,1,Value,1,,,,,,,,\n" +
            "237.653,19,List Change - Next Value,NoCue_Distractor,,17,2,Index,10,Value,1,,,,,,\n" +
            "237.653,21,Group Change Event,Group Change,,17,1,New Group,4,,,,,,,,\n" +
            "237.653,1,Condition Event,Select Trial Type,,4,0,,,,,,,,,,\n" +
            "237.653,32,Whisker - Display Image,mANT,Image,4,2,Position,4,CentralStimulus,0,,,,,,\n" +
            "237.653,16,Variable Event,aTrial_Set,,4,1,Value,831,,,,,,,,\n" +
            "237.653,16,Variable Event,Hold_Time,,4,1,Value,0.181,,,,,,,,\n" +
            "237.653,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\n" +
            "237.653,1,Condition Event,Trial set 831,,5,0,,,,,,,,,,\n" +
            "237.653,16,Variable Event,Target_Position,,5,1,Value,3,,,,,,,,\n" +
            "237.653,16,Variable Event,Incorrect_Position,,5,1,Value,1,,,,,,,,\n" +
            "237.653,21,Group Change Event,Group Change,,5,1,New Group,15,,,,,,,,\n" +
            "237.99,30,Touch Down Event,mANT,,15,4,Position,3,Image 1,0,CoordX,179,CoordY,114,,\n" +
            "238.069,31,Touch Up Event,mANT,,15,4,Position,3,Image 1,0,CoordX,161,CoordY,114,,\n" +
            "238.328,30,Touch Down Event,mANT,,15,4,Position,3,Image 1,0,CoordX,122,CoordY,88,,\n" +
            "238.555,38,Input Transition On Event,FIRBeam #1,,15,0,,,,,,,,,,\n" +
            "238.693,31,Touch Up Event,mANT,,15,4,Position,4,CentralStimulus,0,CoordX,72,CoordY,59,,\n" +
            "238.832,30,Touch Down Event,mANT,,15,4,Position,4,CentralStimulus,0,CoordX,112,CoordY,0,,\n" +
            "238.833,1,Condition Event,Display No Cue,,15,0,,,,,,,,,,\n" +
            "238.833,32,Whisker - Display Image,mANT,Image,15,2,Position,4,dim,3,,,,,,\n" +
            "238.833,29,Timer Event,Hold_Timer,,15,1,Value,0,,,,,,,,\n" +
            "238.833,21,Group Change Event,Group Change,,15,1,New Group,37,,,,,,,,\n" +
            "238.983,1,Condition Event,Hold_Cue,,37,0,,,,,,,,,,\n" +
            "238.983,32,Whisker - Display Image,mANT,Background,37,2,Position,5,Image 1,0,,,,,,\n" +
            "238.983,32,Whisker - Display Image,mANT,Image,37,2,Position,4,dim,3,,,,,,\n" +
            "238.983,21,Group Change Event,Group Change,,37,1,New Group,16,,,,,,,,\n" +
            "239.014,1,Condition Event,Hold_CTI_Distractor,,16,0,,,,,,,,,,\n" +
            "239.014,32,Whisker - Display Image,mANT,Image,16,2,Position,3,Target,1,,,,,,\n" +
            "239.014,32,Whisker - Display Image,mANT,Image,16,2,Position,1,Distractor,2,,,,,,\n" +
            "239.014,16,Variable Event,Hold_Failure_Counter,,16,1,Value,0,,,,,,,,\n" +
            "239.014,29,Timer Event,zzRT_Timer,,16,1,Value,0,,,,,,,,\n" +
            "239.014,29,Timer Event,Target_Timer,,16,1,Value,0,,,,,,,,\n" +
            "239.014,21,Group Change Event,Group Change,,16,1,New Group,7,,,,,,,,\n" +
            "239.488,31,Touch Up Event,mANT,,7,4,Position,3,Target,1,CoordX,145,CoordY,156,,\n" +
            "239.488,1,Condition Event,Leave_During Target,,7,0,,,,,,,,,,\n" +
            "239.488,32,Whisker - Display Image,mANT,Background,7,2,Position,4,Image 1,0,,,,,,\n" +
            "239.488,16,Variable Event,zzRT_Timer,,7,1,Value,0.474,,,,,,,,\n" +
            "239.488,16,Variable Event,Hold_Timer,,7,1,Value,0.655,,,,,,,,\n" +
            "239.488,29,Timer Event,zzMT_Timer,,7,1,Value,0,,,,,,,,\n" +
            "239.488,21,Group Change Event,Group Change,,7,1,New Group,25,,,,,,,,\n" +
            "239.786,30,Touch Down Event,mANT,,25,4,Position,3,Target,1,CoordX,127,CoordY,160,,\n" +
            "239.786,1,Condition Event,Correct_Leaving During Target,,25,0,,,,,,,,,,\n" +
            "239.786,32,Whisker - Display Image,mANT,Background,25,2,Position,3,Image 1,0,,,,,,\n" +
            "239.786,32,Whisker - Display Image,mANT,Background,25,2,Position,1,Image 1,0,,,,,,\n" +
            "239.786,7,Pulse Output Event,Feeder #1,,25,1,Duration,0.28,,,,,,,,\n" +
            "239.786,3,Output On Event,TrayLight #1,,25,0,,,,,,,,,,\n" +
            "239.786,7,Pulse Output Event,Sound_On #1,,25,1,Duration,1,,,,,,,,\n" +
            "239.786,16,Variable Event,Target_Timer,,25,1,Value,0.772,,,,,,,,\n" +
            "239.786,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "239.786,16,Variable Event,zzMT_Timer,,25,1,Value,0.298,,,,,,,,\n" +
            "239.786,21,Group Change Event,Group Change,,25,1,New Group,9,,,,,,,,\n" +
            "239.786,1,Condition Event,Increment Correct_Counter,,9,0,,,,,,,,,,\n" +
            "239.786,16,Variable Event,zz_Correct_Counter,,9,1,Value,13,,,,,,,,\n" +
            "239.786,16,Variable Event,Hold_Success_Counter,,9,1,Value,3,,,,,,,,\n" +
            "239.786,16,Variable Event,aCounter_NoAE,,9,1,Value,26,,,,,,,,\n" +
            "239.786,16,Variable Event,First_Analysis,,9,1,Value,0,,,,,,,,\n" +
            "239.786,16,Variable Event,Change_Hold_Time,,9,1,Value,1,,,,,,,,\n" +
            "239.786,61,Group Maintain Event,Group Change,,9,1,New Group,9,,,,,,,,\n" +
            "240.073,31,Touch Up Event,mANT,,9,4,Position,3,Image 1,0,CoordX,102,CoordY,165,,\n" +
            "240.143,39,Input Transition Off Event,FIRBeam #1,,9,0,,,,,,,,,,\n" +
            "240.335,38,Input Transition On Event,BIRBeam #1,,9,0,,,,,,,,,,\n" +
            "240.35,39,Input Transition Off Event,BIRBeam #1,,9,0,,,,,,,,,,\n" +
            "240.825,38,Input Transition On Event,Tray #1,,9,0,,,,,,,,,,\n" +
            "240.825,1,Condition Event,Reward Collected,,9,0,,,,,,,,,,\n" +
            "240.825,4,Output Off Event,TrayLight #1,,9,0,,,,,,,,,,\n" +
            "240.825,29,Timer Event,After_Reward_Timer,,9,1,Value,0,,,,,,,,\n" +
            "240.825,16,Variable Event,Reward_counter,,9,1,Value,13,,,,,,,,\n" +
            "240.825,21,Group Change Event,Group Change,,9,1,New Group,35,,,,,,,,\n" +
            "240.98,39,Input Transition Off Event,Tray #1,,35,0,,,,,,,,,,\n" +
            "241.649,38,Input Transition On Event,FIRBeam #1,,35,0,,,,,,,,,,\n" +
            "241.669,30,Touch Down Event,mANT,,35,4,Position,4,Image 1,0,CoordX,60,CoordY,103,,\n" +
            "241.685,38,Input Transition On Event,BIRBeam #1,,35,0,,,,,,,,,,\n" +
            "241.747,31,Touch Up Event,mANT,,35,4,Position,4,Image 1,0,CoordX,86,CoordY,103,,\n" +
            "242.198,39,Input Transition Off Event,FIRBeam #1,,35,0,,,,,,,,,,\n" +
            "242.825,1,Condition Event,AfterReward_Pause,,35,0,,,,,,,,,,\n" +
            "242.825,29,Timer Event,ITI_Timer,,35,1,Value,0,,,,,,,,\n" +
            "242.825,16,Variable Event,After_Reward_Timer,,35,1,Value,2,,,,,,,,\n" +
            "242.825,16,Variable Event,After_Reward_Timer,,35,1,Value,0,,,,,,,,\n" +
            "242.825,21,Group Change Event,Group Change,,35,1,New Group,10,,,,,,,,\n";

    public static String distractedTrial4 = "486.433,1,Condition Event,Next trial,,10,0,,,,,,,,,,\n" +
            "486.433,16,Variable Event,ITI_Timer,,10,1,Value,1,,,,,,,,\n" +
            "486.433,16,Variable Event,ITI_Timer,,10,1,Value,0,,,,,,,,\n" +
            "486.433,16,Variable Event,SkipTrial_inc,,10,1,Value,0,,,,,,,,\n" +
            "486.433,16,Variable Event,First_Analysis,,10,1,Value,1,,,,,,,,\n" +
            "486.433,16,Variable Event,Hold_Timer,,10,1,Value,0.958,,,,,,,,\n" +
            "486.433,16,Variable Event,Hold_Timer,,10,1,Value,0,,,,,,,,\n" +
            "486.433,16,Variable Event,zzRT_Timer,,10,1,Value,0.768,,,,,,,,\n" +
            "486.433,16,Variable Event,zzRT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "486.433,16,Variable Event,zzMT_Timer,,10,1,Value,0.979,,,,,,,,\n" +
            "486.433,16,Variable Event,zzMT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "486.433,21,Group Change Event,Group Change,,10,1,New Group,27,,,,,,,,\n" +
            "486.433,1,Condition Event,Change Hold Time,,27,0,,,,,,,,,,\n" +
            "486.433,16,Variable Event,Hold_Time_Adjuster,,27,1,Value,0.189,,,,,,,,\n" +
            "486.433,21,Group Change Event,Group Change,,27,1,New Group,3,,,,,,,,\n" +
            "486.433,1,Condition Event,After 10 trials,,3,0,,,,,,,,,,\n" +
            "486.433,16,Variable Event,_Trial_Counter,,3,1,Value,83,,,,,,,,\n" +
            "486.433,16,Variable Event,Correct_Percent,,3,1,Value,33,,,,,,,,\n" +
            "486.433,16,Variable Event,Correct_Percent,,3,1,Value,0.398,,,,,,,,\n" +
            "486.433,16,Variable Event,Correct_Percent,,3,1,Value,39.759,,,,,,,,\n" +
            "486.433,16,Variable Event,Accuracy_NoAE,,3,1,Value,33,,,,,,,,\n" +
            "486.433,16,Variable Event,Accuracy_NoAE,,3,1,Value,0.589,,,,,,,,\n" +
            "486.433,16,Variable Event,Accuracy_NoAE,,3,1,Value,58.929,,,,,,,,\n" +
            "486.433,16,Variable Event,Validity,,3,1,Value,0,,,,,,,,\n" +
            "486.433,19,List Change - Next Value,Validity_List,,3,2,Index,96,Value,0,,,,,,\n" +
            "486.433,21,Group Change Event,Group Change,,3,1,New Group,17,,,,,,,,\n" +
            "486.433,1,Condition Event,Select Valid Trials,,17,0,,,,,,,,,,\n" +
            "486.433,16,Variable Event,Trial_Type,,17,1,Value,14513,,,,,,,,\n" +
            "486.433,19,List Change - Next Value,Valid_Trials,,17,2,Index,8,Value,14513,,,,,,\n" +
            "486.433,16,Variable Event,Distractor,,17,1,Value,1,,,,,,,,\n" +
            "486.433,19,List Change - Next Value,Valid_Distractor,,17,2,Index,6,Value,1,,,,,,\n" +
            "486.433,21,Group Change Event,Group Change,,17,1,New Group,4,,,,,,,,\n" +
            "486.433,1,Condition Event,Select Trial Type,,4,0,,,,,,,,,,\n" +
            "486.433,32,Whisker - Display Image,mANT,Image,4,2,Position,4,CentralStimulus,0,,,,,,\n" +
            "486.433,16,Variable Event,aTrial_Set,,4,1,Value,14513,,,,,,,,\n" +
            "486.433,16,Variable Event,Hold_Time,,4,1,Value,0.189,,,,,,,,\n" +
            "486.433,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\n" +
            "486.433,1,Condition Event,Trial set 14513,,5,0,,,,,,,,,,\n" +
            "486.433,16,Variable Event,Cue,,5,1,Value,2,,,,,,,,\n" +
            "486.433,16,Variable Event,Target_Position,,5,1,Value,1,,,,,,,,\n" +
            "486.433,16,Variable Event,Incorrect_Position,,5,1,Value,3,,,,,,,,\n" +
            "486.433,21,Group Change Event,Group Change,,5,1,New Group,15,,,,,,,,\n" +
            "486.715,39,Input Transition Off Event,BIRBeam #1,,15,0,,,,,,,,,,\n" +
            "486.866,39,Input Transition Off Event,FIRBeam #1,,15,0,,,,,,,,,,\n" +
            "486.996,30,Touch Down Event,mANT,,15,4,Position,4,CentralStimulus,0,CoordX,41,CoordY,51,,\n" +
            "486.997,1,Condition Event,Display Cue,,15,0,,,,,,,,,,\n" +
            "486.997,32,Whisker - Display Image,mANT,Cue_Images,15,2,Position,4,145,1,,,,,,\n" +
            "486.997,29,Timer Event,Hold_Timer,,15,1,Value,0,,,,,,,,\n" +
            "486.997,21,Group Change Event,Group Change,,15,1,New Group,37,,,,,,,,\n" +
            "487.147,1,Condition Event,Hold_Cue,,37,0,,,,,,,,,,\n" +
            "487.147,32,Whisker - Display Image,mANT,Background,37,2,Position,5,Image 1,0,,,,,,\n" +
            "487.147,32,Whisker - Display Image,mANT,Image,37,2,Position,4,dim,3,,,,,,\n" +
            "487.147,21,Group Change Event,Group Change,,37,1,New Group,16,,,,,,,,\n" +
            "487.185,1,Condition Event,Hold_CTI_Distractor,,16,0,,,,,,,,,,\n" +
            "487.185,32,Whisker - Display Image,mANT,Image,16,2,Position,1,Target,1,,,,,,\n" +
            "487.185,32,Whisker - Display Image,mANT,Image,16,2,Position,3,Distractor,2,,,,,,\n" +
            "487.185,16,Variable Event,Hold_Failure_Counter,,16,1,Value,0,,,,,,,,\n" +
            "487.185,29,Timer Event,zzRT_Timer,,16,1,Value,0,,,,,,,,\n" +
            "487.185,29,Timer Event,Target_Timer,,16,1,Value,0,,,,,,,,\n" +
            "487.185,21,Group Change Event,Group Change,,16,1,New Group,7,,,,,,,,\n" +
            "487.409,38,Input Transition On Event,FIRBeam #1,,7,0,,,,,,,,,,\n" +
            "487.438,31,Touch Up Event,mANT,,7,4,Position,3,Distractor,2,CoordX,56,CoordY,200,,\n" +
            "487.438,1,Condition Event,Leave_During Target,,7,0,,,,,,,,,,\n" +
            "487.438,32,Whisker - Display Image,mANT,Background,7,2,Position,4,Image 1,0,,,,,,\n" +
            "487.438,16,Variable Event,zzRT_Timer,,7,1,Value,0.253,,,,,,,,\n" +
            "487.438,16,Variable Event,Hold_Timer,,7,1,Value,0.441,,,,,,,,\n" +
            "487.438,29,Timer Event,zzMT_Timer,,7,1,Value,0,,,,,,,,\n" +
            "487.438,21,Group Change Event,Group Change,,7,1,New Group,25,,,,,,,,\n" +
            "487.548,39,Input Transition Off Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "488.185,1,Condition Event,Target off,,25,0,,,,,,,,,,\n" +
            "488.185,16,Variable Event,Target_Timer,,25,1,Value,1,,,,,,,,\n" +
            "488.185,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "488.185,32,Whisker - Display Image,mANT,Background,25,2,Position,1,Image 1,0,,,,,,\n" +
            "488.185,32,Whisker - Display Image,mANT,Background,25,2,Position,3,Image 1,0,,,,,,\n" +
            "488.185,61,Group Maintain Event,Group Change,,25,1,New Group,25,,,,,,,,\n" +
            "488.8,38,Input Transition On Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "488.848,39,Input Transition Off Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "489.247,38,Input Transition On Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "489.355,30,Touch Down Event,mANT,,25,4,Position,3,Image 1,0,CoordX,85,CoordY,165,,\n" +
            "489.355,1,Condition Event,Incorrect_Leaving During Target,,25,0,,,,,,,,,,\n" +
            "489.355,32,Whisker - Display Image,mANT,Background,25,2,Position,1,Image 1,0,,,,,,\n" +
            "489.355,32,Whisker - Display Image,mANT,Background,25,2,Position,3,Image 1,0,,,,,,\n" +
            "489.355,16,Variable Event,Timeout,,25,1,Value,1,,,,,,,,\n" +
            "489.355,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "489.355,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "489.355,16,Variable Event,zzMT_Timer,,25,1,Value,1.917,,,,,,,,\n" +
            "489.355,21,Group Change Event,Group Change,,25,1,New Group,32,,,,,,,,\n" +
            "489.355,1,Condition Event,Increment_IncorrectError,,32,0,,,,,,,,,,\n" +
            "489.355,16,Variable Event,zz_Incorrect_Counter,,32,1,Value,15,,,,,,,,\n" +
            "489.355,16,Variable Event,aCounter_NoAE,,32,1,Value,57,,,,,,,,\n" +
            "489.355,16,Variable Event,Change_Hold_Time,,32,1,Value,1,,,,,,,,\n" +
            "489.355,21,Group Change Event,Group Change,,32,1,New Group,11,,,,,,,,\n" +
            "489.355,1,Condition Event,Time Out,,11,0,,,,,,,,,,\n" +
            "489.355,32,Whisker - Display Image,mANT,Background,11,2,Position,4,Image 1,0,,,,,,\n" +
            "489.355,32,Whisker - Display Image,mANT,Background,11,2,Position,1,Image 1,0,,,,,,\n" +
            "489.355,1,Condition Event,TO Houselight On,,11,0,,,,,,,,,,\n" +
            "489.355,3,Output On Event,HouseLight #1,,11,0,,,,,,,,,,\n" +
            "489.355,16,Variable Event,Timeout,,11,1,Value,0,,,,,,,,\n" +
            "489.355,29,Timer Event,Time_Out_Timer,,11,1,Value,0,,,,,,,,\n" +
            "489.355,61,Group Maintain Event,Group Change,,11,1,New Group,11,,,,,,,,\n" +
            "489.355,1,Condition Event,TO Houselight On,,11,0,,,,,,,,,,\n" +
            "489.355,3,Output On Event,HouseLight #1,,11,0,,,,,,,,,,\n" +
            "489.355,21,Group Change Event,Group Change,,11,1,New Group,13,,,,,,,,\n" +
            "489.456,39,Input Transition Off Event,FIRBeam #1,,13,0,,,,,,,,,,\n" +
            "489.509,31,Touch Up Event,mANT,,13,4,Position,3,Image 1,0,CoordX,78,CoordY,182,,\n" +
            "489.534,38,Input Transition On Event,FIRBeam #1,,13,0,,,,,,,,,,\n" +
            "489.612,39,Input Transition Off Event,FIRBeam #1,,13,0,,,,,,,,,,\n" +
            "489.616,38,Input Transition On Event,FIRBeam #1,,13,0,,,,,,,,,,\n" +
            "489.632,39,Input Transition Off Event,FIRBeam #1,,13,0,,,,,,,,,,\n" +
            "489.721,38,Input Transition On Event,BIRBeam #1,,13,0,,,,,,,,,,\n" +
            "489.734,39,Input Transition Off Event,BIRBeam #1,,13,0,,,,,,,,,,\n" +
            "490.355,1,Condition Event,Time Out End,,13,0,,,,,,,,,,\n" +
            "490.355,16,Variable Event,Time_Out_Timer,,13,1,Value,1,,,,,,,,\n" +
            "490.355,16,Variable Event,Time_Out_Timer,,13,1,Value,0,,,,,,,,\n" +
            "490.355,29,Timer Event,ITI_Timer,,13,1,Value,0,,,,,,,,\n" +
            "490.355,21,Group Change Event,Group Change,,13,1,New Group,14,,,,,,,,\n" +
            "490.355,1,Condition Event,Reset Houselight off,,14,0,,,,,,,,,,\n" +
            "490.355,4,Output Off Event,HouseLight #1,,14,0,,,,,,,,,,\n" +
            "490.355,21,Group Change Event,Group Change,,14,1,New Group,10,,,,,,,,\n" +
            "490.515,38,Input Transition On Event,Tray #1,,10,0,,,,,,,,,,\n" +
            "491.073,39,Input Transition Off Event,Tray #1,,10,0,,,,,,,,,,\n" +
            "491.355,1,Condition Event,Next trial,,10,0,,,,,,,,,,";

    public static String undistractedTrial1 = "109.091,16,Variable Event,ITI_Timer,,10,1,Value,1,,,,,,,,\n" +
            "109.091,16,Variable Event,ITI_Timer,,10,1,Value,0,,,,,,,,\n" +
            "109.091,16,Variable Event,SkipTrial_inc,,10,1,Value,0,,,,,,,,\n" +
            "109.091,16,Variable Event,First_Analysis,,10,1,Value,1,,,,,,,,\n" +
            "109.091,16,Variable Event,Hold_Timer,,10,1,Value,7.802,,,,,,,,\n" +
            "109.091,16,Variable Event,Hold_Timer,,10,1,Value,0,,,,,,,,\n" +
            "109.091,16,Variable Event,zzRT_Timer,,10,1,Value,7.607,,,,,,,,\n" +
            "109.091,16,Variable Event,zzRT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "109.091,16,Variable Event,zzMT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "109.091,16,Variable Event,zzMT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "109.091,21,Group Change Event,Group Change,,10,1,New Group,27,,,,,,,,\n" +
            "109.091,1,Condition Event,Change Hold Time,,27,0,,,,,,,,,,\n" +
            "109.091,16,Variable Event,Hold_Time_Adjuster,,27,1,Value,0.175,,,,,,,,\n" +
            "109.091,21,Group Change Event,Group Change,,27,1,New Group,3,,,,,,,,\n" +
            "109.091,1,Condition Event,After 10 trials,,3,0,,,,,,,,,,\n" +
            "109.091,16,Variable Event,_Trial_Counter,,3,1,Value,14,,,,,,,,\n" +
            "109.091,16,Variable Event,Correct_Percent,,3,1,Value,6,,,,,,,,\n" +
            "109.091,16,Variable Event,Correct_Percent,,3,1,Value,0.429,,,,,,,,\n" +
            "109.091,16,Variable Event,Correct_Percent,,3,1,Value,42.857,,,,,,,,\n" +
            "109.091,16,Variable Event,Accuracy_NoAE,,3,1,Value,6,,,,,,,,\n" +
            "109.091,16,Variable Event,Accuracy_NoAE,,3,1,Value,0.545,,,,,,,,\n" +
            "109.091,16,Variable Event,Accuracy_NoAE,,3,1,Value,54.545,,,,,,,,\n" +
            "109.091,16,Variable Event,Validity,,3,1,Value,1,,,,,,,,\n" +
            "109.091,19,List Change - Next Value,Validity_List,,3,2,Index,51,Value,9,,,,,,\n" +
            "109.091,21,Group Change Event,Group Change,,3,1,New Group,17,,,,,,,,\n" +
            "109.091,1,Condition Event,Select Invalid Trials,,17,0,,,,,,,,,,\n" +
            "109.091,16,Variable Event,Trial_Type,,17,1,Value,14531,,,,,,,,\n" +
            "109.091,19,List Change - Next Value,Invalid_Trials,,17,2,Index,1,Value,4513,,,,,,\n" +
            "109.091,16,Variable Event,Distractor,,17,1,Value,0,,,,,,,,\n" +
            "109.091,19,List Change - Next Value,Invalid_Distractor,,17,2,Index,6,Value,1,,,,,,\n" +
            "109.091,21,Group Change Event,Group Change,,17,1,New Group,4,,,,,,,,\n" +
            "109.091,1,Condition Event,Select Trial Type,,4,0,,,,,,,,,,\n" +
            "109.091,32,Whisker - Display Image,mANT,Image,4,2,Position,4,CentralStimulus,0,,,,,,\n" +
            "109.091,16,Variable Event,aTrial_Set,,4,1,Value,14531,,,,,,,,\n" +
            "109.091,16,Variable Event,Hold_Time,,4,1,Value,0.175,,,,,,,,\n" +
            "109.091,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\n" +
            "109.091,1,Condition Event,Trial set 14531,,5,0,,,,,,,,,,\n" +
            "109.091,16,Variable Event,Cue,,5,1,Value,2,,,,,,,,\n" +
            "109.091,16,Variable Event,Target_Position,,5,1,Value,3,,,,,,,,\n" +
            "109.091,16,Variable Event,Incorrect_Position,,5,1,Value,1,,,,,,,,\n" +
            "109.091,21,Group Change Event,Group Change,,5,1,New Group,15,,,,,,,,\n" +
            "147.997,30,Touch Down Event,mANT,,15,4,Position,4,CentralStimulus,0,CoordX,22,CoordY,33,,\n" +
            "147.998,1,Condition Event,Display Cue,,15,0,,,,,,,,,,\n" +
            "147.998,32,Whisker - Display Image,mANT,Cue_Images,15,2,Position,4,145,1,,,,,,\n" +
            "147.998,29,Timer Event,Hold_Timer,,15,1,Value,0,,,,,,,,\n" +
            "147.998,21,Group Change Event,Group Change,,15,1,New Group,37,,,,,,,,\n" +
            "148.148,1,Condition Event,Hold_Cue,,37,0,,,,,,,,,,\n" +
            "148.148,32,Whisker - Display Image,mANT,Background,37,2,Position,5,Image 1,0,,,,,,\n" +
            "148.148,32,Whisker - Display Image,mANT,Image,37,2,Position,4,dim,3,,,,,,\n" +
            "148.148,21,Group Change Event,Group Change,,37,1,New Group,16,,,,,,,,\n" +
            "148.174,1,Condition Event,Hold_CTI,,16,0,,,,,,,,,,\n" +
            "148.174,32,Whisker - Display Image,mANT,Image,16,2,Position,3,Target,1,,,,,,\n" +
            "148.174,16,Variable Event,Hold_Failure_Counter,,16,1,Value,0,,,,,,,,\n" +
            "148.174,29,Timer Event,zzRT_Timer,,16,1,Value,0,,,,,,,,\n" +
            "148.174,29,Timer Event,Target_Timer,,16,1,Value,0,,,,,,,,\n" +
            "148.174,21,Group Change Event,Group Change,,16,1,New Group,7,,,,,,,,\n" +
            "148.419,31,Touch Up Event,mANT,,7,4,Position,4,dim,3,CoordX,72,CoordY,54,,\n" +
            "148.42,1,Condition Event,Leave_During Target,,7,0,,,,,,,,,,\n" +
            "148.42,32,Whisker - Display Image,mANT,Background,7,2,Position,4,Image 1,0,,,,,,\n" +
            "148.42,16,Variable Event,zzRT_Timer,,7,1,Value,0.246,,,,,,,,\n" +
            "148.42,16,Variable Event,Hold_Timer,,7,1,Value,0.421,,,,,,,,\n" +
            "148.42,29,Timer Event,zzMT_Timer,,7,1,Value,0,,,,,,,,\n" +
            "148.42,21,Group Change Event,Group Change,,7,1,New Group,25,,,,,,,,\n" +
            "148.429,38,Input Transition On Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "148.453,39,Input Transition Off Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "148.981,38,Input Transition On Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "149.087,39,Input Transition Off Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "149.174,1,Condition Event,Target off,,25,0,,,,,,,,,,\n" +
            "149.174,16,Variable Event,Target_Timer,,25,1,Value,1,,,,,,,,\n" +
            "149.174,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "149.174,32,Whisker - Display Image,mANT,Background,25,2,Position,3,Image 1,0,,,,,,\n" +
            "149.174,32,Whisker - Display Image,mANT,Background,25,2,Position,1,Image 1,0,,,,,,\n" +
            "149.174,61,Group Maintain Event,Group Change,,25,1,New Group,25,,,,,,,,\n" +
            "149.204,38,Input Transition On Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "149.421,39,Input Transition Off Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "149.437,30,Touch Down Event,mANT,,25,4,Position,3,Image 1,0,CoordX,37,CoordY,168,,\n" +
            "149.437,1,Condition Event,Correct_Leaving During Target,,25,0,,,,,,,,,,\n" +
            "149.437,32,Whisker - Display Image,mANT,Background,25,2,Position,3,Image 1,0,,,,,,\n" +
            "149.437,32,Whisker - Display Image,mANT,Background,25,2,Position,1,Image 1,0,,,,,,\n" +
            "149.437,7,Pulse Output Event,Feeder #1,,25,1,Duration,0.28,,,,,,,,\n" +
            "149.437,3,Output On Event,TrayLight #1,,25,0,,,,,,,,,,\n" +
            "149.437,7,Pulse Output Event,Sound_On #1,,25,1,Duration,1,,,,,,,,\n" +
            "149.437,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "149.437,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "149.437,16,Variable Event,zzMT_Timer,,25,1,Value,1.018,,,,,,,,\n" +
            "149.437,21,Group Change Event,Group Change,,25,1,New Group,9,,,,,,,,\n" +
            "149.437,1,Condition Event,Increment Correct_Counter,,9,0,,,,,,,,,,\n" +
            "149.437,16,Variable Event,zz_Correct_Counter,,9,1,Value,7,,,,,,,,\n" +
            "149.437,16,Variable Event,Hold_Success_Counter,,9,1,Value,5,,,,,,,,\n" +
            "149.437,16,Variable Event,aCounter_NoAE,,9,1,Value,12,,,,,,,,\n" +
            "149.437,16,Variable Event,First_Analysis,,9,1,Value,0,,,,,,,,\n" +
            "149.437,16,Variable Event,Change_Hold_Time,,9,1,Value,1,,,,,,,,\n" +
            "149.437,61,Group Maintain Event,Group Change,,9,1,New Group,9,,,,,,,,\n" +
            "149.592,31,Touch Up Event,mANT,,9,4,Position,3,Image 1,0,CoordX,37,CoordY,165,,\n" +
            "150.189,38,Input Transition On Event,Tray #1,,9,0,,,,,,,,,,\n" +
            "150.19,1,Condition Event,Reward Collected,,9,0,,,,,,,,,,\n" +
            "150.19,4,Output Off Event,TrayLight #1,,9,0,,,,,,,,,,\n" +
            "150.19,29,Timer Event,After_Reward_Timer,,9,1,Value,0,,,,,,,,\n" +
            "150.19,16,Variable Event,Reward_counter,,9,1,Value,7,,,,,,,,\n" +
            "150.19,21,Group Change Event,Group Change,,9,1,New Group,35,,,,,,,,\n" +
            "150.485,39,Input Transition Off Event,Tray #1,,35,0,,,,,,,,,,\n" +
            "151.005,38,Input Transition On Event,BIRBeam #1,,35,0,,,,,,,,,,\n" +
            "152.19,1,Condition Event,AfterReward_Pause,,35,0,,,,,,,,,,\n" +
            "152.19,29,Timer Event,ITI_Timer,,35,1,Value,0,,,,,,,,\n" +
            "152.19,16,Variable Event,After_Reward_Timer,,35,1,Value,2,,,,,,,,\n" +
            "152.19,16,Variable Event,After_Reward_Timer,,35,1,Value,0,,,,,,,,\n" +
            "152.19,21,Group Change Event,Group Change,,35,1,New Group,10,,,,,,,,\n" +
            "152.343,39,Input Transition Off Event,BIRBeam #1,,10,0,,,,,,,,,,\n" +
            "152.517,38,Input Transition On Event,BIRBeam #1,,10,0,,,,,,,,,,\n" +
            "153.19,1,Condition Event,Next trial,,10,0,,,,,,,,,,\n";

    public static String undistractedTrial2 = "212.614,16,Variable Event,ITI_Timer,,10,1,Value,1,,,,,,,,\n" +
            "212.614,16,Variable Event,ITI_Timer,,10,1,Value,0,,,,,,,,\n" +
            "212.614,16,Variable Event,SkipTrial_inc,,10,1,Value,0,,,,,,,,\n" +
            "212.614,16,Variable Event,First_Analysis,,10,1,Value,1,,,,,,,,\n" +
            "212.614,16,Variable Event,Hold_Timer,,10,1,Value,0.211,,,,,,,,\n" +
            "212.614,16,Variable Event,Hold_Timer,,10,1,Value,0,,,,,,,,\n" +
            "212.614,16,Variable Event,zzRT_Timer,,10,1,Value,0.031,,,,,,,,\n" +
            "212.614,16,Variable Event,zzRT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "212.614,16,Variable Event,zzMT_Timer,,10,1,Value,0.04,,,,,,,,\n" +
            "212.614,16,Variable Event,zzMT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "212.614,21,Group Change Event,Group Change,,10,1,New Group,27,,,,,,,,\n" +
            "212.614,1,Condition Event,Change Hold Time,,27,0,,,,,,,,,,\n" +
            "212.614,16,Variable Event,Hold_Time_Adjuster,,27,1,Value,0.184,,,,,,,,\n" +
            "212.614,21,Group Change Event,Group Change,,27,1,New Group,3,,,,,,,,\n" +
            "212.614,1,Condition Event,After 10 trials,,3,0,,,,,,,,,,\n" +
            "212.614,16,Variable Event,_Trial_Counter,,3,1,Value,29,,,,,,,,\n" +
            "212.614,16,Variable Event,Correct_Percent,,3,1,Value,10,,,,,,,,\n" +
            "212.614,16,Variable Event,Correct_Percent,,3,1,Value,0.345,,,,,,,,\n" +
            "212.614,16,Variable Event,Correct_Percent,,3,1,Value,34.483,,,,,,,,\n" +
            "212.614,16,Variable Event,Accuracy_NoAE,,3,1,Value,10,,,,,,,,\n" +
            "212.614,16,Variable Event,Accuracy_NoAE,,3,1,Value,0.476,,,,,,,,\n" +
            "212.614,16,Variable Event,Accuracy_NoAE,,3,1,Value,47.619,,,,,,,,\n" +
            "212.614,16,Variable Event,Validity,,3,1,Value,0,,,,,,,,\n" +
            "212.614,19,List Change - Next Value,Validity_List,,3,2,Index,61,Value,1,,,,,,\n" +
            "212.614,21,Group Change Event,Group Change,,3,1,New Group,17,,,,,,,,\n" +
            "212.614,1,Condition Event,Select Valid Trials,,17,0,,,,,,,,,,\n" +
            "212.614,16,Variable Event,Trial_Type,,17,1,Value,14513,,,,,,,,\n" +
            "212.614,19,List Change - Next Value,Valid_Trials,,17,2,Index,6,Value,14513,,,,,,\n" +
            "212.614,16,Variable Event,Distractor,,17,1,Value,0,,,,,,,,\n" +
            "212.614,19,List Change - Next Value,Valid_Distractor,,17,2,Index,2,Value,0,,,,,,\n" +
            "212.614,21,Group Change Event,Group Change,,17,1,New Group,4,,,,,,,,\n" +
            "212.614,1,Condition Event,Select Trial Type,,4,0,,,,,,,,,,\n" +
            "212.614,32,Whisker - Display Image,mANT,Image,4,2,Position,4,CentralStimulus,0,,,,,,\n" +
            "212.614,16,Variable Event,aTrial_Set,,4,1,Value,14513,,,,,,,,\n" +
            "212.614,16,Variable Event,Hold_Time,,4,1,Value,0.184,,,,,,,,\n" +
            "212.614,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\n" +
            "212.614,1,Condition Event,Trial set 14513,,5,0,,,,,,,,,,\n" +
            "212.614,16,Variable Event,Cue,,5,1,Value,2,,,,,,,,\n" +
            "212.614,16,Variable Event,Target_Position,,5,1,Value,1,,,,,,,,\n" +
            "212.614,16,Variable Event,Incorrect_Position,,5,1,Value,3,,,,,,,,\n" +
            "212.614,21,Group Change Event,Group Change,,5,1,New Group,15,,,,,,,,\n" +
            "213.18,38,Input Transition On Event,BIRBeam #1,,15,0,,,,,,,,,,\n" +
            "213.191,39,Input Transition Off Event,BIRBeam #1,,15,0,,,,,,,,,,\n" +
            "213.499,30,Touch Down Event,mANT,,15,4,Position,4,CentralStimulus,0,CoordX,78,CoordY,19,,\n" +
            "213.5,1,Condition Event,Display Cue,,15,0,,,,,,,,,,\n" +
            "213.5,32,Whisker - Display Image,mANT,Cue_Images,15,2,Position,4,145,1,,,,,,\n" +
            "213.5,29,Timer Event,Hold_Timer,,15,1,Value,0,,,,,,,,\n" +
            "213.5,21,Group Change Event,Group Change,,15,1,New Group,37,,,,,,,,\n" +
            "213.65,1,Condition Event,Hold_Cue,,37,0,,,,,,,,,,\n" +
            "213.65,32,Whisker - Display Image,mANT,Background,37,2,Position,5,Image 1,0,,,,,,\n" +
            "213.65,32,Whisker - Display Image,mANT,Image,37,2,Position,4,dim,3,,,,,,\n" +
            "213.65,21,Group Change Event,Group Change,,37,1,New Group,16,,,,,,,,\n" +
            "213.684,1,Condition Event,Hold_CTI,,16,0,,,,,,,,,,\n" +
            "213.684,32,Whisker - Display Image,mANT,Image,16,2,Position,1,Target,1,,,,,,\n" +
            "213.684,16,Variable Event,Hold_Failure_Counter,,16,1,Value,0,,,,,,,,\n" +
            "213.684,29,Timer Event,zzRT_Timer,,16,1,Value,0,,,,,,,,\n" +
            "213.684,29,Timer Event,Target_Timer,,16,1,Value,0,,,,,,,,\n" +
            "213.684,21,Group Change Event,Group Change,,16,1,New Group,7,,,,,,,,\n" +
            "213.787,31,Touch Up Event,mANT,,7,4,Position,3,Image 1,0,CoordX,50,CoordY,217,,\n" +
            "213.787,1,Condition Event,Leave_During Target,,7,0,,,,,,,,,,\n" +
            "213.787,32,Whisker - Display Image,mANT,Background,7,2,Position,4,Image 1,0,,,,,,\n" +
            "213.787,16,Variable Event,zzRT_Timer,,7,1,Value,0.103,,,,,,,,\n" +
            "213.787,16,Variable Event,Hold_Timer,,7,1,Value,0.287,,,,,,,,\n" +
            "213.787,29,Timer Event,zzMT_Timer,,7,1,Value,0,,,,,,,,\n" +
            "213.787,21,Group Change Event,Group Change,,7,1,New Group,25,,,,,,,,\n" +
            "214.304,30,Touch Down Event,mANT,,25,4,Position,3,Image 1,0,CoordX,71,CoordY,128,,\n" +
            "214.305,1,Condition Event,Incorrect_Leaving During Target,,25,0,,,,,,,,,,\n" +
            "214.305,32,Whisker - Display Image,mANT,Background,25,2,Position,1,Image 1,0,,,,,,\n" +
            "214.305,32,Whisker - Display Image,mANT,Background,25,2,Position,3,Image 1,0,,,,,,\n" +
            "214.305,16,Variable Event,Timeout,,25,1,Value,1,,,,,,,,\n" +
            "214.305,16,Variable Event,Target_Timer,,25,1,Value,0.621,,,,,,,,\n" +
            "214.305,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "214.305,16,Variable Event,zzMT_Timer,,25,1,Value,0.518,,,,,,,,\n" +
            "214.305,21,Group Change Event,Group Change,,25,1,New Group,32,,,,,,,,\n" +
            "214.305,1,Condition Event,Increment_IncorrectError,,32,0,,,,,,,,,,\n" +
            "214.305,16,Variable Event,zz_Incorrect_Counter,,32,1,Value,7,,,,,,,,\n" +
            "214.305,16,Variable Event,aCounter_NoAE,,32,1,Value,22,,,,,,,,\n" +
            "214.305,16,Variable Event,Change_Hold_Time,,32,1,Value,1,,,,,,,,\n" +
            "214.305,21,Group Change Event,Group Change,,32,1,New Group,11,,,,,,,,\n" +
            "214.305,1,Condition Event,Time Out,,11,0,,,,,,,,,,\n" +
            "214.305,32,Whisker - Display Image,mANT,Background,11,2,Position,4,Image 1,0,,,,,,\n" +
            "214.305,32,Whisker - Display Image,mANT,Background,11,2,Position,1,Image 1,0,,,,,,\n" +
            "214.305,1,Condition Event,TO Houselight On,,11,0,,,,,,,,,,\n" +
            "214.305,3,Output On Event,HouseLight #1,,11,0,,,,,,,,,,\n" +
            "214.305,16,Variable Event,Timeout,,11,1,Value,0,,,,,,,,\n" +
            "214.305,29,Timer Event,Time_Out_Timer,,11,1,Value,0,,,,,,,,\n" +
            "214.305,61,Group Maintain Event,Group Change,,11,1,New Group,11,,,,,,,,\n" +
            "214.305,1,Condition Event,TO Houselight On,,11,0,,,,,,,,,,\n" +
            "214.305,3,Output On Event,HouseLight #1,,11,0,,,,,,,,,,\n" +
            "214.305,21,Group Change Event,Group Change,,11,1,New Group,13,,,,,,,,\n" +
            "214.437,31,Touch Up Event,mANT,,13,4,Position,3,Image 1,0,CoordX,71,CoordY,114,,\n" +
            "214.926,38,Input Transition On Event,FIRBeam #1,,13,0,,,,,,,,,,\n" +
            "215.049,39,Input Transition Off Event,FIRBeam #1,,13,0,,,,,,,,,,\n" +
            "215.092,38,Input Transition On Event,BIRBeam #1,,13,0,,,,,,,,,,\n" +
            "215.305,1,Condition Event,Time Out End,,13,0,,,,,,,,,,\n" +
            "215.305,16,Variable Event,Time_Out_Timer,,13,1,Value,1,,,,,,,,\n" +
            "215.305,16,Variable Event,Time_Out_Timer,,13,1,Value,0,,,,,,,,\n" +
            "215.305,29,Timer Event,ITI_Timer,,13,1,Value,0,,,,,,,,\n" +
            "215.305,21,Group Change Event,Group Change,,13,1,New Group,14,,,,,,,,\n" +
            "215.305,1,Condition Event,Reset Houselight off,,14,0,,,,,,,,,,\n" +
            "215.305,4,Output Off Event,HouseLight #1,,14,0,,,,,,,,,,\n" +
            "215.305,21,Group Change Event,Group Change,,14,1,New Group,10,,,,,,,,\n" +
            "216.175,38,Input Transition On Event,FIRBeam #1,,10,0,,,,,,,,,,\n" +
            "216.258,39,Input Transition Off Event,FIRBeam #1,,10,0,,,,,,,,,,\n" +
            "216.305,1,Condition Event,Next trial,,10,0,,,,,,,,,,\n";

    public static String undistractedTrial3 = "224.352,16,Variable Event,ITI_Timer,,10,1,Value,1,,,,,,,,\n" +
            "224.352,16,Variable Event,ITI_Timer,,10,1,Value,0,,,,,,,,\n" +
            "224.352,16,Variable Event,SkipTrial_inc,,10,1,Value,0,,,,,,,,\n" +
            "224.352,16,Variable Event,First_Analysis,,10,1,Value,1,,,,,,,,\n" +
            "224.352,16,Variable Event,Hold_Timer,,10,1,Value,0.387,,,,,,,,\n" +
            "224.352,16,Variable Event,Hold_Timer,,10,1,Value,0,,,,,,,,\n" +
            "224.352,16,Variable Event,zzRT_Timer,,10,1,Value,0.188,,,,,,,,\n" +
            "224.352,16,Variable Event,zzRT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "224.352,16,Variable Event,zzMT_Timer,,10,1,Value,0.519,,,,,,,,\n" +
            "224.352,16,Variable Event,zzMT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "224.352,21,Group Change Event,Group Change,,10,1,New Group,27,,,,,,,,\n" +
            "224.352,1,Condition Event,Change Hold Time,,27,0,,,,,,,,,,\n" +
            "224.352,16,Variable Event,Hold_Time_Adjuster,,27,1,Value,0.195,,,,,,,,\n" +
            "224.352,21,Group Change Event,Group Change,,27,1,New Group,3,,,,,,,,\n" +
            "224.352,1,Condition Event,After 10 trials,,3,0,,,,,,,,,,\n" +
            "224.352,16,Variable Event,_Trial_Counter,,3,1,Value,32,,,,,,,,\n" +
            "224.352,16,Variable Event,Correct_Percent,,3,1,Value,11,,,,,,,,\n" +
            "224.352,16,Variable Event,Correct_Percent,,3,1,Value,0.344,,,,,,,,\n" +
            "224.352,16,Variable Event,Correct_Percent,,3,1,Value,34.375,,,,,,,,\n" +
            "224.352,16,Variable Event,Accuracy_NoAE,,3,1,Value,11,,,,,,,,\n" +
            "224.352,16,Variable Event,Accuracy_NoAE,,3,1,Value,0.478,,,,,,,,\n" +
            "224.352,16,Variable Event,Accuracy_NoAE,,3,1,Value,47.826,,,,,,,,\n" +
            "224.352,16,Variable Event,Validity,,3,1,Value,9,,,,,,,,\n" +
            "224.352,19,List Change - Next Value,Validity_List,,3,2,Index,63,Value,9,,,,,,\n" +
            "224.352,21,Group Change Event,Group Change,,3,1,New Group,17,,,,,,,,\n" +
            "224.352,1,Condition Event,Select Alerting Trials,,17,0,,,,,,,,,,\n" +
            "224.352,16,Variable Event,Trial_Type,,17,1,Value,931,,,,,,,,\n" +
            "224.352,19,List Change - Next Value,Alerting_Trials,,17,2,Index,9,Value,931,,,,,,\n" +
            "224.352,16,Variable Event,Distractor,,17,1,Value,0,,,,,,,,\n" +
            "224.352,19,List Change - Next Value,Alerting_Distractor,,17,2,Index,2,Value,0,,,,,,\n" +
            "224.352,21,Group Change Event,Group Change,,17,1,New Group,4,,,,,,,,\n" +
            "224.352,1,Condition Event,Select Trial Type,,4,0,,,,,,,,,,\n" +
            "224.352,32,Whisker - Display Image,mANT,Image,4,2,Position,4,CentralStimulus,0,,,,,,\n" +
            "224.352,16,Variable Event,aTrial_Set,,4,1,Value,931,,,,,,,,\n" +
            "224.352,16,Variable Event,Hold_Time,,4,1,Value,0.195,,,,,,,,\n" +
            "224.352,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\n" +
            "224.352,1,Condition Event,Trial set 931,,5,0,,,,,,,,,,\n" +
            "224.352,16,Variable Event,Target_Position,,5,1,Value,3,,,,,,,,\n" +
            "224.352,16,Variable Event,Incorrect_Position,,5,1,Value,1,,,,,,,,\n" +
            "224.352,21,Group Change Event,Group Change,,5,1,New Group,15,,,,,,,,\n" +
            "224.701,39,Input Transition Off Event,FIRBeam #1,,15,0,,,,,,,,,,\n" +
            "224.725,30,Touch Down Event,mANT,,15,4,Position,4,CentralStimulus,0,CoordX,15,CoordY,42,,\n" +
            "224.726,1,Condition Event,Display Alerting Cue,,15,0,,,,,,,,,,\n" +
            "224.726,32,Whisker - Display Image,mANT,Image,15,2,Position,4,dim,3,,,,,,\n" +
            "224.726,32,Whisker - Display Image,mANT,Cue_Images,15,2,Position,5,Frame,2,,,,,,\n" +
            "224.726,29,Timer Event,Hold_Timer,,15,1,Value,0,,,,,,,,\n" +
            "224.726,21,Group Change Event,Group Change,,15,1,New Group,37,,,,,,,,\n" +
            "224.876,1,Condition Event,Hold_Cue,,37,0,,,,,,,,,,\n" +
            "224.876,32,Whisker - Display Image,mANT,Background,37,2,Position,5,Image 1,0,,,,,,\n" +
            "224.876,32,Whisker - Display Image,mANT,Image,37,2,Position,4,dim,3,,,,,,\n" +
            "224.876,21,Group Change Event,Group Change,,37,1,New Group,16,,,,,,,,\n" +
            "224.92,1,Condition Event,Hold_CTI,,16,0,,,,,,,,,,\n" +
            "224.92,32,Whisker - Display Image,mANT,Image,16,2,Position,3,Target,1,,,,,,\n" +
            "224.92,16,Variable Event,Hold_Failure_Counter,,16,1,Value,0,,,,,,,,\n" +
            "224.92,29,Timer Event,zzRT_Timer,,16,1,Value,0,,,,,,,,\n" +
            "224.92,29,Timer Event,Target_Timer,,16,1,Value,0,,,,,,,,\n" +
            "224.92,21,Group Change Event,Group Change,,16,1,New Group,7,,,,,,,,\n" +
            "224.956,31,Touch Up Event,mANT,,7,4,Position,4,dim,3,CoordX,22,CoordY,51,,\n" +
            "224.956,1,Condition Event,Leave_During Target,,7,0,,,,,,,,,,\n" +
            "224.956,32,Whisker - Display Image,mANT,Background,7,2,Position,4,Image 1,0,,,,,,\n" +
            "224.956,16,Variable Event,zzRT_Timer,,7,1,Value,0.036,,,,,,,,\n" +
            "224.956,16,Variable Event,Hold_Timer,,7,1,Value,0.23,,,,,,,,\n" +
            "224.956,29,Timer Event,zzMT_Timer,,7,1,Value,0,,,,,,,,\n" +
            "224.956,21,Group Change Event,Group Change,,7,1,New Group,25,,,,,,,,\n" +
            "224.977,38,Input Transition On Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "225.92,1,Condition Event,Target off,,25,0,,,,,,,,,,\n" +
            "225.92,16,Variable Event,Target_Timer,,25,1,Value,1,,,,,,,,\n" +
            "225.92,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "225.92,32,Whisker - Display Image,mANT,Background,25,2,Position,3,Image 1,0,,,,,,\n" +
            "225.92,32,Whisker - Display Image,mANT,Background,25,2,Position,1,Image 1,0,,,,,,\n" +
            "225.92,61,Group Maintain Event,Group Change,,25,1,New Group,25,,,,,,,,\n" +
            "226.196,39,Input Transition Off Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "226.283,38,Input Transition On Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "226.513,39,Input Transition Off Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "226.652,38,Input Transition On Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "226.785,39,Input Transition Off Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "226.834,30,Touch Down Event,mANT,,25,4,Position,1,Image 1,0,CoordX,179,CoordY,178,,\n" +
            "226.834,1,Condition Event,Incorrect_Leaving During Target,,25,0,,,,,,,,,,\n" +
            "226.834,32,Whisker - Display Image,mANT,Background,25,2,Position,3,Image 1,0,,,,,,\n" +
            "226.834,32,Whisker - Display Image,mANT,Background,25,2,Position,1,Image 1,0,,,,,,\n" +
            "226.834,16,Variable Event,Timeout,,25,1,Value,1,,,,,,,,\n" +
            "226.834,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "226.834,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "226.834,16,Variable Event,zzMT_Timer,,25,1,Value,1.878,,,,,,,,\n" +
            "226.834,21,Group Change Event,Group Change,,25,1,New Group,32,,,,,,,,\n" +
            "226.834,1,Condition Event,Increment_IncorrectError,,32,0,,,,,,,,,,\n" +
            "226.834,16,Variable Event,zz_Incorrect_Counter,,32,1,Value,8,,,,,,,,\n" +
            "226.834,16,Variable Event,aCounter_NoAE,,32,1,Value,24,,,,,,,,\n" +
            "226.834,16,Variable Event,Change_Hold_Time,,32,1,Value,1,,,,,,,,\n" +
            "226.834,21,Group Change Event,Group Change,,32,1,New Group,11,,,,,,,,\n" +
            "226.834,1,Condition Event,Time Out,,11,0,,,,,,,,,,\n" +
            "226.834,32,Whisker - Display Image,mANT,Background,11,2,Position,4,Image 1,0,,,,,,\n" +
            "226.834,32,Whisker - Display Image,mANT,Background,11,2,Position,3,Image 1,0,,,,,,\n" +
            "226.834,1,Condition Event,TO Houselight On,,11,0,,,,,,,,,,\n" +
            "226.834,3,Output On Event,HouseLight #1,,11,0,,,,,,,,,,\n" +
            "226.834,16,Variable Event,Timeout,,11,1,Value,0,,,,,,,,\n" +
            "226.834,29,Timer Event,Time_Out_Timer,,11,1,Value,0,,,,,,,,\n" +
            "226.834,61,Group Maintain Event,Group Change,,11,1,New Group,11,,,,,,,,\n" +
            "226.834,1,Condition Event,TO Houselight On,,11,0,,,,,,,,,,\n" +
            "226.834,3,Output On Event,HouseLight #1,,11,0,,,,,,,,,,\n" +
            "226.834,21,Group Change Event,Group Change,,11,1,New Group,13,,,,,,,,\n" +
            "227.028,31,Touch Up Event,mANT,,13,4,Position,1,Image 1,0,CoordX,181,CoordY,174,,\n" +
            "227.119,38,Input Transition On Event,FIRBeam #1,,13,0,,,,,,,,,,\n" +
            "227.191,39,Input Transition Off Event,FIRBeam #1,,13,0,,,,,,,,,,\n" +
            "227.834,1,Condition Event,Time Out End,,13,0,,,,,,,,,,\n" +
            "227.834,16,Variable Event,Time_Out_Timer,,13,1,Value,1,,,,,,,,\n" +
            "227.834,16,Variable Event,Time_Out_Timer,,13,1,Value,0,,,,,,,,\n" +
            "227.834,29,Timer Event,ITI_Timer,,13,1,Value,0,,,,,,,,\n" +
            "227.834,21,Group Change Event,Group Change,,13,1,New Group,14,,,,,,,,\n" +
            "227.834,1,Condition Event,Reset Houselight off,,14,0,,,,,,,,,,\n" +
            "227.834,4,Output Off Event,HouseLight #1,,14,0,,,,,,,,,,\n" +
            "227.834,21,Group Change Event,Group Change,,14,1,New Group,10,,,,,,,,\n" +
            "228.124,38,Input Transition On Event,Tray #1,,10,0,,,,,,,,,,\n" +
            "228.248,39,Input Transition Off Event,Tray #1,,10,0,,,,,,,,,,\n" +
            "228.49,38,Input Transition On Event,BIRBeam #1,,10,0,,,,,,,,,,\n" +
            "228.505,39,Input Transition Off Event,BIRBeam #1,,10,0,,,,,,,,,,\n";

    public static String undistractedTrial4 = "359.074,1,Condition Event,Next trial,,10,0,,,,,,,,,,\n" +
            "359.074,16,Variable Event,ITI_Timer,,10,1,Value,1,,,,,,,,\n" +
            "359.074,16,Variable Event,ITI_Timer,,10,1,Value,0,,,,,,,,\n" +
            "359.074,16,Variable Event,SkipTrial_inc,,10,1,Value,0,,,,,,,,\n" +
            "359.074,16,Variable Event,First_Analysis,,10,1,Value,1,,,,,,,,\n" +
            "359.074,16,Variable Event,Hold_Timer,,10,1,Value,0.889,,,,,,,,\n" +
            "359.074,16,Variable Event,Hold_Timer,,10,1,Value,0,,,,,,,,\n" +
            "359.074,16,Variable Event,zzRT_Timer,,10,1,Value,0.709,,,,,,,,\n" +
            "359.074,16,Variable Event,zzRT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "359.074,16,Variable Event,zzMT_Timer,,10,1,Value,0.979,,,,,,,,\n" +
            "359.074,16,Variable Event,zzMT_Timer,,10,1,Value,0,,,,,,,,\n" +
            "359.074,21,Group Change Event,Group Change,,10,1,New Group,27,,,,,,,,\n" +
            "359.074,1,Condition Event,Change Hold Time,,27,0,,,,,,,,,,\n" +
            "359.074,16,Variable Event,Hold_Time_Adjuster,,27,1,Value,0.182,,,,,,,,\n" +
            "359.074,21,Group Change Event,Group Change,,27,1,New Group,3,,,,,,,,\n" +
            "359.074,1,Condition Event,After 10 trials,,3,0,,,,,,,,,,\n" +
            "359.074,16,Variable Event,_Trial_Counter,,3,1,Value,59,,,,,,,,\n" +
            "359.074,16,Variable Event,Correct_Percent,,3,1,Value,22,,,,,,,,\n" +
            "359.074,16,Variable Event,Correct_Percent,,3,1,Value,0.373,,,,,,,,\n" +
            "359.074,16,Variable Event,Correct_Percent,,3,1,Value,37.288,,,,,,,,\n" +
            "359.074,16,Variable Event,Accuracy_NoAE,,3,1,Value,22,,,,,,,,\n" +
            "359.074,16,Variable Event,Accuracy_NoAE,,3,1,Value,0.55,,,,,,,,\n" +
            "359.074,16,Variable Event,Accuracy_NoAE,,3,1,Value,55,,,,,,,,\n" +
            "359.074,16,Variable Event,Validity,,3,1,Value,0,,,,,,,,\n" +
            "359.074,19,List Change - Next Value,Validity_List,,3,2,Index,80,Value,0,,,,,,\n" +
            "359.074,21,Group Change Event,Group Change,,3,1,New Group,17,,,,,,,,\n" +
            "359.074,1,Condition Event,Select Valid Trials,,17,0,,,,,,,,,,\n" +
            "359.074,16,Variable Event,Trial_Type,,17,1,Value,4531,,,,,,,,\n" +
            "359.074,19,List Change - Next Value,Valid_Trials,,17,2,Index,3,Value,4531,,,,,,\n" +
            "359.074,16,Variable Event,Distractor,,17,1,Value,0,,,,,,,,\n" +
            "359.074,19,List Change - Next Value,Valid_Distractor,,17,2,Index,5,Value,1,,,,,,\n" +
            "359.074,21,Group Change Event,Group Change,,17,1,New Group,4,,,,,,,,\n" +
            "359.074,1,Condition Event,Select Trial Type,,4,0,,,,,,,,,,\n" +
            "359.074,32,Whisker - Display Image,mANT,Image,4,2,Position,4,CentralStimulus,0,,,,,,\n" +
            "359.074,16,Variable Event,aTrial_Set,,4,1,Value,4531,,,,,,,,\n" +
            "359.074,16,Variable Event,Hold_Time,,4,1,Value,0.182,,,,,,,,\n" +
            "359.074,21,Group Change Event,Group Change,,4,1,New Group,5,,,,,,,,\n" +
            "359.074,1,Condition Event,Trial set 4531,,5,0,,,,,,,,,,\n" +
            "359.074,16,Variable Event,Cue,,5,1,Value,1,,,,,,,,\n" +
            "359.074,16,Variable Event,Target_Position,,5,1,Value,3,,,,,,,,\n" +
            "359.074,16,Variable Event,Incorrect_Position,,5,1,Value,1,,,,,,,,\n" +
            "359.074,21,Group Change Event,Group Change,,5,1,New Group,15,,,,,,,,\n" +
            "359.386,38,Input Transition On Event,FIRBeam #1,,15,0,,,,,,,,,,\n" +
            "359.503,39,Input Transition Off Event,FIRBeam #1,,15,0,,,,,,,,,,\n" +
            "359.51,38,Input Transition On Event,FIRBeam #1,,15,0,,,,,,,,,,\n" +
            "359.599,39,Input Transition Off Event,FIRBeam #1,,15,0,,,,,,,,,,\n" +
            "359.647,38,Input Transition On Event,FIRBeam #1,,15,0,,,,,,,,,,\n" +
            "359.679,39,Input Transition Off Event,FIRBeam #1,,15,0,,,,,,,,,,\n" +
            "359.707,39,Input Transition Off Event,BIRBeam #1,,15,0,,,,,,,,,,\n" +
            "359.744,30,Touch Down Event,mANT,,15,4,Position,4,CentralStimulus,0,CoordX,22,CoordY,68,,\n" +
            "359.745,1,Condition Event,Display Cue,,15,0,,,,,,,,,,\n" +
            "359.745,32,Whisker - Display Image,mANT,Cue_Images,15,2,Position,4,45,0,,,,,,\n" +
            "359.745,29,Timer Event,Hold_Timer,,15,1,Value,0,,,,,,,,\n" +
            "359.745,21,Group Change Event,Group Change,,15,1,New Group,37,,,,,,,,\n" +
            "359.875,38,Input Transition On Event,FIRBeam #1,,37,0,,,,,,,,,,\n" +
            "359.895,1,Condition Event,Hold_Cue,,37,0,,,,,,,,,,\n" +
            "359.895,32,Whisker - Display Image,mANT,Background,37,2,Position,5,Image 1,0,,,,,,\n" +
            "359.895,32,Whisker - Display Image,mANT,Image,37,2,Position,4,dim,3,,,,,,\n" +
            "359.895,21,Group Change Event,Group Change,,37,1,New Group,16,,,,,,,,\n" +
            "359.927,1,Condition Event,Hold_CTI,,16,0,,,,,,,,,,\n" +
            "359.927,32,Whisker - Display Image,mANT,Image,16,2,Position,3,Target,1,,,,,,\n" +
            "359.927,16,Variable Event,Hold_Failure_Counter,,16,1,Value,0,,,,,,,,\n" +
            "359.927,29,Timer Event,zzRT_Timer,,16,1,Value,0,,,,,,,,\n" +
            "359.927,29,Timer Event,Target_Timer,,16,1,Value,0,,,,,,,,\n" +
            "359.927,21,Group Change Event,Group Change,,16,1,New Group,7,,,,,,,,\n" +
            "360.103,39,Input Transition Off Event,FIRBeam #1,,7,0,,,,,,,,,,\n" +
            "360.174,38,Input Transition On Event,FIRBeam #1,,7,0,,,,,,,,,,\n" +
            "360.207,39,Input Transition Off Event,FIRBeam #1,,7,0,,,,,,,,,,\n" +
            "360.281,31,Touch Up Event,mANT,,7,4,Position,1,Image 1,0,CoordX,138,CoordY,217,,\n" +
            "360.281,1,Condition Event,Leave_During Target,,7,0,,,,,,,,,,\n" +
            "360.281,32,Whisker - Display Image,mANT,Background,7,2,Position,4,Image 1,0,,,,,,\n" +
            "360.281,16,Variable Event,zzRT_Timer,,7,1,Value,0.354,,,,,,,,\n" +
            "360.281,16,Variable Event,Hold_Timer,,7,1,Value,0.536,,,,,,,,\n" +
            "360.281,29,Timer Event,zzMT_Timer,,7,1,Value,0,,,,,,,,\n" +
            "360.281,21,Group Change Event,Group Change,,7,1,New Group,25,,,,,,,,\n" +
            "360.433,38,Input Transition On Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "360.547,38,Input Transition On Event,BIRBeam #1,,25,0,,,,,,,,,,\n" +
            "360.895,39,Input Transition Off Event,BIRBeam #1,,25,0,,,,,,,,,,\n" +
            "360.927,1,Condition Event,Target off,,25,0,,,,,,,,,,\n" +
            "360.927,16,Variable Event,Target_Timer,,25,1,Value,1,,,,,,,,\n" +
            "360.927,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "360.927,32,Whisker - Display Image,mANT,Background,25,2,Position,3,Image 1,0,,,,,,\n" +
            "360.927,32,Whisker - Display Image,mANT,Background,25,2,Position,1,Image 1,0,,,,,,\n" +
            "360.927,61,Group Maintain Event,Group Change,,25,1,New Group,25,,,,,,,,\n" +
            "361.06,39,Input Transition Off Event,FIRBeam #1,,25,0,,,,,,,,,,\n" +
            "361.18,30,Touch Down Event,mANT,,25,4,Position,3,Image 1,0,CoordX,53,CoordY,174,,\n" +
            "361.18,1,Condition Event,Correct_Leaving During Target,,25,0,,,,,,,,,,\n" +
            "361.18,32,Whisker - Display Image,mANT,Background,25,2,Position,3,Image 1,0,,,,,,\n" +
            "361.18,32,Whisker - Display Image,mANT,Background,25,2,Position,1,Image 1,0,,,,,,\n" +
            "361.18,7,Pulse Output Event,Feeder #1,,25,1,Duration,0.28,,,,,,,,\n" +
            "361.18,3,Output On Event,TrayLight #1,,25,0,,,,,,,,,,\n" +
            "361.18,7,Pulse Output Event,Sound_On #1,,25,1,Duration,1,,,,,,,,\n" +
            "361.18,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "361.18,16,Variable Event,Target_Timer,,25,1,Value,0,,,,,,,,\n" +
            "361.18,16,Variable Event,zzMT_Timer,,25,1,Value,0.899,,,,,,,,\n" +
            "361.18,21,Group Change Event,Group Change,,25,1,New Group,9,,,,,,,,\n" +
            "361.18,1,Condition Event,Increment Correct_Counter,,9,0,,,,,,,,,,\n" +
            "361.18,16,Variable Event,zz_Correct_Counter,,9,1,Value,23,,,,,,,,\n" +
            "361.18,16,Variable Event,Hold_Success_Counter,,9,1,Value,2,,,,,,,,\n" +
            "361.18,16,Variable Event,aCounter_NoAE,,9,1,Value,41,,,,,,,,\n" +
            "361.18,16,Variable Event,First_Analysis,,9,1,Value,0,,,,,,,,\n" +
            "361.18,16,Variable Event,Change_Hold_Time,,9,1,Value,1,,,,,,,,\n" +
            "361.18,61,Group Maintain Event,Group Change,,9,1,New Group,9,,,,,,,,\n" +
            "361.354,31,Touch Up Event,mANT,,9,4,Position,3,Image 1,0,CoordX,37,CoordY,182,,\n" +
            "361.383,38,Input Transition On Event,FIRBeam #1,,9,0,,,,,,,,,,\n" +
            "361.465,39,Input Transition Off Event,FIRBeam #1,,9,0,,,,,,,,,,\n" +
            "361.565,38,Input Transition On Event,BIRBeam #1,,9,0,,,,,,,,,,\n" +
            "361.591,39,Input Transition Off Event,BIRBeam #1,,9,0,,,,,,,,,,\n" +
            "361.828,38,Input Transition On Event,Tray #1,,9,0,,,,,,,,,,\n" +
            "361.828,1,Condition Event,Reward Collected,,9,0,,,,,,,,,,\n" +
            "361.828,4,Output Off Event,TrayLight #1,,9,0,,,,,,,,,,\n" +
            "361.828,29,Timer Event,After_Reward_Timer,,9,1,Value,0,,,,,,,,\n" +
            "361.828,16,Variable Event,Reward_counter,,9,1,Value,23,,,,,,,,\n" +
            "361.828,21,Group Change Event,Group Change,,9,1,New Group,35,,,,,,,,\n" +
            "362.044,39,Input Transition Off Event,Tray #1,,35,0,,,,,,,,,,\n" +
            "362.246,38,Input Transition On Event,BIRBeam #1,,35,0,,,,,,,,,,\n" +
            "362.29,39,Input Transition Off Event,BIRBeam #1,,35,0,,,,,,,,,,\n" +
            "362.503,38,Input Transition On Event,FIRBeam #1,,35,0,,,,,,,,,,\n" +
            "362.566,38,Input Transition On Event,BIRBeam #1,,35,0,,,,,,,,,,\n" +
            "363.828,1,Condition Event,AfterReward_Pause,,35,0,,,,,,,,,,\n" +
            "363.828,29,Timer Event,ITI_Timer,,35,1,Value,0,,,,,,,,\n" +
            "363.828,16,Variable Event,After_Reward_Timer,,35,1,Value,2,,,,,,,,\n" +
            "363.828,16,Variable Event,After_Reward_Timer,,35,1,Value,0,,,,,,,,\n" +
            "363.828,21,Group Change Event,Group Change,,35,1,New Group,10,,,,,,,,\n" +
            "364.004,39,Input Transition Off Event,BIRBeam #1,,10,0,,,,,,,,,,\n" +
            "364.828,1,Condition Event,Next trial,,10,0,,,,,,,,,,\n";


}
