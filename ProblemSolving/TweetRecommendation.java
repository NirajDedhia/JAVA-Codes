
import java.io.*;
import java.util.*;

class tw1
{
	public static void main(String[] args)
	{
		int[][] followGraph_edges = {{4,3},{1,2},{1,3},{1,4},{5,6}};
		int[][] likeGraph_edges = {{2,10},{3,10},{4,10},{2,11},{3,12},{4,11}};
		int targetUser = 1;
		int minLikeThreshold = 3;
		int a[] = getRecommendedTweets(followGraph_edges, likeGraph_edges, targetUser, minLikeThreshold);

		for(int i=0; i<a.length; i++)
		{
			System.out.println(a[i]);
		}
	}

	public static int[] getRecommendedTweets (int[][] followGraph_edges, int[][] likeGraph_edges, int targetUser, int minLikeThreshold)
	{
		
		HashMap tweetLikedBy = new HashMap();
		HashSet iFollows = new HashSet();

		addMyfollowers(followGraph_edges, iFollows, targetUser);
		notetweetLikedByMyFollowers(iFollows, likeGraph_edges, tweetLikedBy);
		int returnResult[] = filterTweet(tweetLikedBy, minLikeThreshold);
		Arrays.sort(returnResult);

		return (returnResult);
	}

	/**
	* This method adds all user's followers to hashset.
	* Using hashset for fast fetching.
	*
	* param: followGraph_edges 
    * param: iFollows stores user's followers
	* param: user
	*
	* Rreturn: void
	*/
	public static void addMyfollowers(int[][] followGraph_edges, HashSet iFollows, int targetUser)
	{
		for(int i=0; i<followGraph_edges.length; i++)
		{
			if(followGraph_edges[i][0] == targetUser)
				iFollows.add(followGraph_edges[i][1]);
		}
	}

	/**
	* This method adds all the tweets liked by user's followers.
	*
	* param: iFollows stores user's followers 
    * param: likeGraph_edges
	* param: tweetLikedBy stores all tweets liked by user's followers
	*
	* Rreturn: void
	*/
	public static void notetweetLikedByMyFollowers(HashSet iFollows, int[][] likeGraph_edges, HashMap tweetLikedBy)
	{
		for(int i=0; i<likeGraph_edges.length; i++)
		{
			if(iFollows.contains(likeGraph_edges[i][0]))
			{
				if(tweetLikedBy.containsKey(likeGraph_edges[i][1]))
				{
					tweetLikedBy.put(likeGraph_edges[i][1], (int)tweetLikedBy.get(likeGraph_edges[i][1])+1);
				}
				else
				{
					tweetLikedBy.put(likeGraph_edges[i][1], 1);
				}
			}
		}
	}

	/**
	* This method filters tweets based on threshold given.
	*
	* param: tweetLikedBy stores all tweets liked by user's followers
    * param: minLikeThreshold
	*
	* Rreturn: unsorted result i.e. recommended tweets array
	*/
	public static int[] filterTweet(HashMap tweetLikedBy, int minLikeThreshold)
	{
		ArrayList<Integer> result = new ArrayList<Integer>();
		Iterator i = tweetLikedBy.entrySet().iterator();
      	while(i.hasNext()) 
      	{
	        Map.Entry me = (Map.Entry)i.next();
	        int tweet = (int)me.getValue();
	        if(tweet>=minLikeThreshold)
	        	result.add((int)me.getKey());
      	}

	    int j = -1;
	    int returnResult[] = new int[result.size()];
	    for(Integer ele: result)
	    {
	    	j++;
	    	returnResult[j] = (int)ele;
	    }

	    return returnResult;
	}
}