package api.weka.winloss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.HattrickObjectCreator;
import api.entity.MatchDetails;
import api.entity.matchdetails.Team;
import api.exception.IllegalXMLException;
import api.weka.AttributeAndType;
import api.weka.WekaFileCreator;

public class RatingProportionWinLossWekaFileCreator extends WekaFileCreator {
	
	public static void main(String[] args) {
		RatingProportionWinLossWekaFileCreator creator = new RatingProportionWinLossWekaFileCreator();
		creator.createAndBuildWekaFile();
	}

	@Override
	protected String getFileName() {
		return "RatingProportions_WinLoss";
	}

	@Override
	protected List<AttributeAndType> getAttributeList() {
		List<AttributeAndType> attributeList = new ArrayList<AttributeAndType>();
		attributeList.add(new AttributeAndType("diff_ratingMidField", "NUMERIC"));
		attributeList.add(new AttributeAndType("diff_ratingRightDef_LeftAtt", "NUMERIC"));
		attributeList.add(new AttributeAndType("diff_ratingMidDef_MidAtt", "NUMERIC"));
		attributeList.add(new AttributeAndType("diff_ratingLeftDef_RightAtt", "NUMERIC"));
		attributeList.add(new AttributeAndType("diff_ratingRightAtt_LeftDef", "NUMERIC"));
		attributeList.add(new AttributeAndType("diff_ratingMidAtt_MidDef", "NUMERIC"));
		attributeList.add(new AttributeAndType("diff_ratingLeftAtt_RightDef", "NUMERIC"));

		attributeList.add(new AttributeAndType("result", "{win,loss}"));
		return attributeList;
	}

	@Override
	protected String getDataForMatch(int matchId) throws IOException, IllegalXMLException {
	MatchDetails matchDetails = new HattrickObjectCreator().getMatchDetails(matchId);
	Team homeTeam = matchDetails.getMatch().getHomeTeam();
	Team awayTeam = matchDetails.getMatch().getAwayTeam();
	int homeAdvantage = homeTeam.getGoals() - awayTeam.getGoals();
	if(homeAdvantage == 0)
		return "";
	
	String dataString = "";
	dataString += (double)homeTeam.getRatingMidField().getValue()/((double)homeTeam.getRatingMidField().getValue() + (double)awayTeam.getRatingMidField().getValue()) + ",";
	dataString += (double)homeTeam.getRatingRightDef().getValue()/((double)homeTeam.getRatingRightDef().getValue() + (double)awayTeam.getRatingLeftAtt().getValue()) + ",";
	dataString += (double)homeTeam.getRatingMidDef().getValue()/((double)homeTeam.getRatingMidDef().getValue() + (double)awayTeam.getRatingMidAtt().getValue()) + ",";
	dataString += (double)homeTeam.getRatingLeftDef().getValue()/((double)homeTeam.getRatingLeftDef().getValue() + (double)awayTeam.getRatingRightAtt().getValue()) + ",";
	dataString += (double)homeTeam.getRatingRightAtt().getValue()/((double)homeTeam.getRatingRightAtt().getValue() + (double)awayTeam.getRatingLeftDef().getValue()) + ",";
	dataString += (double)homeTeam.getRatingMidAtt().getValue()/((double)homeTeam.getRatingMidAtt().getValue() + (double)awayTeam.getRatingMidDef().getValue()) + ",";
	dataString += (double)homeTeam.getRatingLeftAtt().getValue()/((double)homeTeam.getRatingLeftAtt().getValue() + (double)awayTeam.getRatingRightDef().getValue()) + ",";
	
	if(homeAdvantage > 0)
		dataString += "win";
	else
		dataString += "loss";
	return dataString + "\n";
	}

}
