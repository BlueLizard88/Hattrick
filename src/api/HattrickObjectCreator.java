package api;

import java.io.IOException;
import java.util.Calendar;

import api.entity.ArenaDetails;
import api.entity.LeagueFixtures;
import api.entity.MatchDetails;
import api.entity.MatchLineup;
import api.entity.MatchesArchive;
import api.entity.PlayerDetails;
import api.entity.TeamDetails;
import api.entity.Training;
import api.entity.WorldDetails;
import api.exception.DiscardException;
import api.exception.IllegalXMLException;
import api.parser.XMLArenaDetailsParser;
import api.parser.XMLLeagueFixturesParser;
import api.parser.XMLMatchDetailsParser;
import api.parser.XMLMatchLineupParser;
import api.parser.XMLMatchesArchiveParser;
import api.parser.XMLPlayerDetailsParser;
import api.parser.XMLTeamDetailsParser;
import api.parser.XMLTrainingParser;
import api.parser.XMLWorldDetailsParser;

public class HattrickObjectCreator {
	
	private HattrickDownloader downloader;
	private HattrickXMLCollector xmlCollector;
	
	public static void main(String[] args) throws IllegalXMLException {
		Training t = new HattrickObjectCreator().getTraining(321576);
	}
	
	public HattrickObjectCreator()
	{
		this.downloader = new HattrickDownloader();
		this.xmlCollector = new HattrickXMLCollector();
	}
	
	private HattrickDownloader getDownloader() {
		return downloader;
	}

	private HattrickXMLCollector getXmlCollector() {
		return xmlCollector;
	}

	public ArenaDetails getArenaDetails(int arenaId) throws IOException, IllegalXMLException {
		return XMLArenaDetailsParser.parseArenaDetailsFromString(getDownloader().getArenaDetailsString(arenaId));
	}
	
	public ArenaDetails getArenaDetailsFromFile(int arenaId) throws IllegalXMLException
	{
		return XMLArenaDetailsParser.parseArenaDetailsFromString(
				getXmlCollector().readStringFromXMLFile(
					LocalPaths.XML_LOCATION + LocalPaths.ARENA_DETAILS_DIRECTORY + arenaId + ".xml"));
	}
	
	public MatchLineup getMatchLineup(int matchId, int teamId) throws IOException, IllegalXMLException {
		return XMLMatchLineupParser.parseMatchLineupFromString(getDownloader().getMatchLineupString(matchId, teamId));
	}
	
	public MatchLineup getMatchLineupFromFile(int leagueId, int matchId, int teamId) throws IllegalXMLException
	{
		return XMLMatchLineupParser.parseMatchLineupFromString(
				getXmlCollector().readStringFromXMLFile(
						LocalPaths.getFullDirectoryPath(leagueId) + LocalPaths.MATCH_LINEUP_DIRECTORY + matchId + "_" + teamId + ".xml"));
	}
	
	public MatchLineup getMatchLineupFromAdvancedFile(String directoryPath, int matchId, int teamId) throws IllegalXMLException
	{
		return XMLMatchLineupParser.parseMatchLineupFromString(
				getXmlCollector().readStringFromXMLFile(
						directoryPath + LocalPaths.MATCH_LINEUP_DIRECTORY + matchId + "_" + teamId + ".xml"));
	}
	
	public MatchDetails getMatchDetails(int matchId) throws IOException, IllegalXMLException, DiscardException {
		return XMLMatchDetailsParser.parseMatchDetailsFromString(getDownloader().getMatchDetailsString(matchId));
	}
	
	public Training getTraining(int teamId) throws IllegalXMLException {
		return XMLTrainingParser.parseTrainingFromString(getDownloader().getTrainingString(teamId));
	}
	
	public MatchDetails getMatchDetailsFromSimpleFile(int matchId) throws IllegalXMLException, DiscardException
	{
		return XMLMatchDetailsParser.parseMatchDetailsFromString(
				getXmlCollector().readStringFromXMLFile(
						LocalPaths.XML_5000 
						+ LocalPaths.MATCH_DETAILS_DIRECTORY + matchId + ".xml"));
	}
	
	public MatchDetails getMatchDetailsFromFile(int leagueId, int matchId) throws IllegalXMLException, DiscardException
	{
		return XMLMatchDetailsParser.parseMatchDetailsFromString(
				getXmlCollector().readStringFromXMLFile(
						LocalPaths.getFullDirectoryPath(leagueId) 
						+ LocalPaths.MATCH_DETAILS_DIRECTORY + matchId + ".xml"));
	}
	
	public MatchDetails getMatchDetailsFromAdvancedFile(String directoryPath, int matchId) throws IllegalXMLException, DiscardException
	{
		return XMLMatchDetailsParser.parseMatchDetailsFromString(
				getXmlCollector().readStringFromXMLFile(
						directoryPath + LocalPaths.MATCH_DETAILS_DIRECTORY + matchId + ".xml"));
	}
	
	public PlayerDetails getPlayerDetails(int playerId) throws IOException, IllegalXMLException {
		return XMLPlayerDetailsParser.parsePlayerDetailsFromString(getDownloader().getPlayerDetailsString(playerId));
	}
	
	public PlayerDetails getPlayerDetailsFromFile(String path, int playerId) throws IllegalXMLException
	{
		return XMLPlayerDetailsParser.parsePlayerDetailsFromString(
				getXmlCollector().readStringFromXMLFile(
						path + LocalPaths.PLAYER_DETAILS_DIRECTORY + String.valueOf(playerId) + ".xml"));
	}
	
	public LeagueFixtures getLeagueFixtures(int leagueLevelUnitID) throws IOException, IllegalXMLException {
		return XMLLeagueFixturesParser.parseLeagueFixturesFromString(getDownloader().getLeagueFixturesString(leagueLevelUnitID));
	}
	
	public LeagueFixtures getLeagueFixturesFromFile(int leagueLevelUnitID) throws IllegalXMLException
	{
		return XMLLeagueFixturesParser.parseLeagueFixturesFromString(
				getXmlCollector().readStringFromXMLFile(
					LocalPaths.XML_LOCATION + LocalPaths.LEAGUE_FIXTURES_DIRECTORY + String.valueOf(leagueLevelUnitID) + ".xml"));
	}
	
	public WorldDetails getWorldDetails() throws IOException, IllegalXMLException {
		return XMLWorldDetailsParser.parseWorldDetailsFromString(getDownloader().getWorldDetailsString());
	}
	
	public WorldDetails getWorldDetailsFromFile() throws IllegalXMLException
	{
		return XMLWorldDetailsParser.parseWorldDetailsFromString(
				getXmlCollector().readStringFromXMLFile(
					LocalPaths.XML_LOCATION + LocalPaths.WORLD_DETAILS_DIRECTORY + "worldDetails.xml"));
	}
	
	public MatchesArchive getMatchesArchive(int teamID) throws IOException, IllegalXMLException {
		return XMLMatchesArchiveParser.parseMatchesArchiveFromString(getDownloader().getMatchesArchiveString(teamID));
	}
	
	public MatchesArchive getMatchesArchive(int teamID, Calendar fromDate) throws IOException, IllegalXMLException {
		return XMLMatchesArchiveParser.parseMatchesArchiveFromString(getDownloader().getMatchesArchiveString(teamID, fromDate));
	}
	
	public MatchesArchive getMatchesArchiveFromFile(int teamID) throws IllegalXMLException
	{
		return XMLMatchesArchiveParser.parseMatchesArchiveFromString(
				getXmlCollector().readStringFromXMLFile(
					LocalPaths.XML_LOCATION + LocalPaths.MATCHES_ARCHIVE_DIRECTORY + teamID + ".xml"));
	}
	
	public TeamDetails getTeamDetails(int teamID) throws IOException, IllegalXMLException {
		return XMLTeamDetailsParser.parseTeamDetailsFromString(getDownloader().getTeamDetailsString(teamID));
	}
	
	public TeamDetails getTeamDetailsFromFile(int teamID) throws IllegalXMLException
	{
		return XMLTeamDetailsParser.parseTeamDetailsFromString(
				getXmlCollector().readStringFromXMLFile(
					LocalPaths.XML_LOCATION + LocalPaths.TEAM_DETAILS_DIRECTORY + teamID + ".xml"));
	}

}
