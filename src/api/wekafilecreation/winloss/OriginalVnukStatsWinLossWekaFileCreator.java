package api.wekafilecreation.winloss;

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

public class OriginalVnukStatsWinLossWekaFileCreator extends WekaFileCreator {

	@Override
	protected String getFileName() {
		return "OriginalVnukStats_WinLoss";
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
	protected String getDataForMatch(int matchId) throws IOException,
			IllegalXMLException, DiscardException {
		MatchDetails matchDetails = new HattrickObjectCreator().getMatchDetailsFromSimpleFile(matchId);
		Team homeTeam = matchDetails.getMatch().getHomeTeam();
		Team awayTeam = matchDetails.getMatch().getAwayTeam();
		int homeAdvantage = homeTeam.getGoals() - awayTeam.getGoals();
		
		String dataString = "";
		dataString += homeTeam.getOriginalVnukStats() + ",";
		
		dataString += awayTeam.getOriginalVnukStats() + ",";
		
		if(homeAdvantage > 0)
			dataString += "win";
		else
			dataString += "loss";
		return dataString + "\n";
	}

}
