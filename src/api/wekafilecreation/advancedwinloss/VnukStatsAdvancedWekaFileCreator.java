package api.wekafilecreation.advancedwinloss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.HattrickObjectCreator;
import api.entity.MatchDetails;
import api.entity.matchdetails.Team;
import api.exception.DiscardException;
import api.exception.IllegalXMLException;
import api.wekafilecreation.AdvancedWekaFileCreator;
import api.wekafilecreation.AttributeAndType;

public class VnukStatsAdvancedWekaFileCreator extends
		AdvancedWekaFileCreator {

	@Override
	protected String getFileName() {
		return "advancedVnukStats_blub";
	}

	@Override
	protected List<AttributeAndType> getAttributeList() {
		List<AttributeAndType> attributeList = new ArrayList<AttributeAndType>();
		
		attributeList.add(new AttributeAndType("home_vnukStats", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_vnukStats", "NUMERIC"));

		attributeList.add(new AttributeAndType("result", "{win,loss}"));
		return attributeList;
	}

	@Override
	protected String getDataForMatch(int leagueId, int matchId, String directoryPath)
			throws IOException, IllegalXMLException, DiscardException {

		MatchDetails matchDetails = new HattrickObjectCreator().getMatchDetailsFromAdvancedFile(directoryPath, matchId);
	
		Team homeTeam = matchDetails.getMatch().getHomeTeam();
		Team awayTeam = matchDetails.getMatch().getAwayTeam();
		int homeAdvantage = homeTeam.getGoals() - awayTeam.getGoals();
//		if(homeAdvantage == 0)
//			return "";
		
		String dataString = "";
		
		dataString += homeTeam.getVnukStats() + ",";
		dataString += awayTeam.getVnukStats() + ",";
		
		if(homeAdvantage > 0)
			dataString += "win";
		if(homeAdvantage == 0)
			dataString += "tie";
		else
			dataString += "loss";
		return dataString + "\n";
	}

}
