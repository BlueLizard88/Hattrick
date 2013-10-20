package api.weka.goaldiff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.HattrickObjectCreator;
import api.entity.MatchDetails;
import api.entity.matchdetails.Team;
import api.exception.IllegalXMLException;
import api.weka.AttributeAndType;
import api.weka.WekaFileCreator;

public class RatingDifferenceGoalDiffWekaFileCreator extends WekaFileCreator {
	
	public static void main(String[] args) {
		RatingDifferenceGoalDiffWekaFileCreator creator = new RatingDifferenceGoalDiffWekaFileCreator();
		creator.createAndBuildWekaFile();
	}

	@Override
	protected String getFileName() {
		return "RatingDifferences_GoalDiff";
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

		attributeList.add(new AttributeAndType("result", "NUMERIC"));
		return attributeList;
	}

	@Override
	protected String getDataForMatch(int matchId) throws IOException, IllegalXMLException {
	MatchDetails matchDetails = new HattrickObjectCreator().getMatchDetails(matchId);
	Team homeTeam = matchDetails.getMatch().getHomeTeam();
	Team awayTeam = matchDetails.getMatch().getAwayTeam();
	int homeAdvantage = homeTeam.getGoals() - awayTeam.getGoals();
	
	String dataString = "";
	dataString += (homeTeam.getRatingMidField().getValue() - awayTeam.getRatingMidField().getValue()) + ",";
	dataString += (homeTeam.getRatingRightDef().getValue() - awayTeam.getRatingLeftAtt().getValue()) + ",";
	dataString += (homeTeam.getRatingMidDef().getValue() - awayTeam.getRatingMidAtt().getValue()) + ",";
	dataString += (homeTeam.getRatingLeftDef().getValue() - awayTeam.getRatingRightAtt().getValue()) + ",";
	dataString += (homeTeam.getRatingRightAtt().getValue() - awayTeam.getRatingLeftDef().getValue()) + ",";
	dataString += (homeTeam.getRatingMidAtt().getValue() - awayTeam.getRatingMidDef().getValue()) + ",";
	dataString += (homeTeam.getRatingLeftAtt().getValue() - awayTeam.getRatingRightDef().getValue()) + ",";
	
	dataString += homeAdvantage;
	return dataString + "\n";
	}

}