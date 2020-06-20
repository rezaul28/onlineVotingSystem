package sample;

public class Result {
    private String name;
    private final int ballotNo;
    private String post;
    private final int voteCount;

    //constructor
    Result(String name,int ballotNo,String post,int voteCount){
        this.name = name;
        this.ballotNo = ballotNo;
        this.post = post;
        this.voteCount = voteCount;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBallotNo() {
        return ballotNo;
    }

    public int getVoteCount() {
        return voteCount;
    }
}