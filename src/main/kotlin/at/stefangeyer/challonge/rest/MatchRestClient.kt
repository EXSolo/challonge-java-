package at.stefangeyer.challonge.rest

import at.stefangeyer.challonge.model.Match
import at.stefangeyer.challonge.model.enumeration.MatchState
import at.stefangeyer.challonge.model.query.MatchQuery
import at.stefangeyer.challonge.rest.exception.DataAccessException

/**
 * Match Rest Client Definition
 *
 * @author Stefan Geyer
 * @version 2018-06-29
 */
interface MatchRestClient {

    /**
     * Retrieve a tournament's match list.
     *
     * @param tournament    Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                      If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                      (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param participantId Only retrieve matches that include the specified participant.
     *                      This parameter is optional. Provide null if you want to skip it.
     * @param state         all (default), pending, open, complete.
     *                      This parameter is optional. Provide null if you want to skip it.
     * @throws DataAccessException Exchange with the rest api failed
     * @return The tournament's matches
     */
    @Throws(DataAccessException::class)
    fun getMatches(tournament: String, participantId: Int, state: MatchState): List<Match>

    /**
     * Retrieve a single match record for a tournament.
     *
     * @param tournament         Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                           If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                           (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId            The match's unique ID
     * @param includeAttachments Include an array of associated attachment records
     * @throws DataAccessException Exchange with the rest api failed
     * @return The requested match
     */
    @Throws(DataAccessException::class)
    fun getMatch(tournament: String, matchId: Int, includeAttachments: Boolean): Match

    /**
     * Update/submit the score(s) for a match.
     *
     * @param tournament Tournament ID (e.g. 10230) or URL (e.g. 'single_elim' for challonge.com/single_elim).
     *                   If assigned to a subdomain, URL format must be :subdomain-:tournament_url
     *                   (e.g. 'test-mytourney' for test.challonge.com/mytourney)
     * @param matchId    The match's unique ID
     * @param match      The match data
     * @throws DataAccessException Exchange with the rest api failed
     * @return The updated match
     */
    @Throws(DataAccessException::class)
    fun updateMatch(tournament: String, matchId: Int, match: MatchQuery): Match
}