package api.wekafilecreation.goaldiff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.HattrickObjectCreator;
import api.entity.MatchDetails;
import api.entity.matchdetails.Team;
import api.exception.DiscardException;
import api.exception.IllegalXMLException;
import api.wekafilecreation.AttributeAndType;
import api.wekafilecreation.WekaFileCreator;

public class NaiveGoalDiffWekaFileCreator extends WekaFileCreator {
	
	@Override
	protected String getDataForMatch(int matchId) throws IOException, IllegalXMLException, DiscardException {
		MatchDetails matchDetails = new HattrickObjectCreator().getMatchDetailsFromSimpleFile(matchId);
		Team homeTeam = matchDetails.getMatch().getHomeTeam();
		Team awayTeam = matchDetails.getMatch().getAwayTeam();
		int homeAdvantage = homeTeam.getGoals() - awayTeam.getGoals();
		
		String dataString = "";
		
		dataString += homeTeam.getRatingMidField().getValue() + ",";
		dataString += homeTeam.getRatingRightDef().getValue() + ",";
		dataString += homeTeam.getRatingMidDef().getValue() + ",";
		dataString += homeTeam.getRatingLeftDef().getValue() + ",";
		dataString += homeTeam.getRatingRightAtt().getValue() + ",";
		dataString += homeTeam.getRatingMidAtt().getValue() + ",";
		dataString += homeTeam.getRatingLeftAtt().getValue() + ",";
		
		dataString += awayTeam.getRatingMidField().getValue() + ",";
		dataString += awayTeam.getRatingRightDef().getValue() + ",";
		dataString += awayTeam.getRatingMidDef().getValue() + ",";
		dataString += awayTeam.getRatingLeftDef().getValue() + ",";
		dataString += awayTeam.getRatingRightAtt().getValue() + ",";
		dataString += awayTeam.getRatingMidAtt().getValue() + ",";
		dataString += awayTeam.getRatingLeftAtt().getValue() + ",";
		dataString += homeAdvantage;
		return dataString + "\n";
	}

	@Override
	protected String getFileName() {
		return "Naive_GoalDiff";
	}

	@Override
	protected List<AttributeAndType> getAttributeList() {
		
		List<AttributeAndType> attributeList = new ArrayList<AttributeAndType>();
		attributeList.add(new AttributeAndType("home_ratingMidField", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_ratingRightDef", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_ratingMidDef", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_ratingLeftDef", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_ratingRightAtt", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_ratingMidAtt", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_ratingLeftAtt", "NUMERIC"));
		
		attributeList.add(new AttributeAndType("away_ratingMidField", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_ratingRightDef", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_ratingMidDef", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_ratingLeftDef", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_ratingRightAtt", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_ratingMidAtt", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_ratingLeftAtt", "NUMERIC"));

		attributeList.add(new AttributeAndType("result", "NUMERIC"));
		return attributeList;
	}

}
