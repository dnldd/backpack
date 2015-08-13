package dnldd.backpack.utils;

public class FacebookUtils {
    /*protected static Session session;
    protected static boolean isFetching;
    protected static String email;
    protected static GraphUser userInfo;
    protected static String profileID;
    protected static String profilePictureURI;

    public Session getSession(){return session; }
    public String getEmail(){ return email; }
    public String getProfileID(){ return profileID; }
    public String getProfilePictureURI(){ return profilePictureURI; }

    private static void performFacebookLogin(BaseActivity activity) {
        final Session.NewPermissionsRequest permissionsRequest = new Session.NewPermissionsRequest(activity, Arrays.asList("public_profile, email"));
        session = Session.openActiveSession(activity, true, new Session.StatusCallback() {
            @Override
            public void call(Session session, SessionState state, Exception exception) {
                if (session.isOpened() && !isFetching) {
                    isFetching = true;
                    session.requestNewReadPermissions(permissionsRequest);
                    Request request = Request.newMeRequest(session, new Request.GraphUserCallback() {
                        @Override
                        public void onCompleted(GraphUser user, Response response) {
                            if (user != null) {
                                org.json.JSONObject graphResponse = response.getGraphObject().getInnerJSONObject();
                                email = graphResponse.optString("email");
                                userInfo = user;
                                profileID = graphResponse.optString("id");
                                profilePictureURI = "https://graph.facebook.com/"+ user.getId() + "/picture?type=large";
                            }
                        }
                    });
                    request.executeAsync();
                } else {
                    if (!session.isOpened()) LogUtils.log(LogUtils.INFO_LOG_TYPE, "the facebook session is not opened");
                    else LogUtils.log(LogUtils.INFO_LOG_TYPE, "the facebook session is still fetching");

                }
            }
        });
    }*/
}
