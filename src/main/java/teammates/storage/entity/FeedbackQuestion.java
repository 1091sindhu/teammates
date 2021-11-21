package teammates.storage.entity;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.OnSave;
import com.googlecode.objectify.annotation.Translate;
import com.googlecode.objectify.annotation.Unindex;

import teammates.common.datatransfer.FeedbackParticipantType;
import teammates.common.datatransfer.questions.FeedbackQuestionType;
import teammates.common.util.Const;

/**
 * Represents a feedback question.
 */
@Entity
@Index
public class FeedbackQuestion extends BaseEntity {

    @Id
    private Long feedbackQuestionId;

    private String feedbackSessionName;

    private String courseId;

    /**
     * Serialized {@link teammates.common.datatransfer.questions.FeedbackQuestionDetails} stored as a string.
     *
     * @see teammates.common.datatransfer.attributes.FeedbackQuestionAttributes#getQuestionDetailsCopy()
     */
    @Unindex
    private String questionText;

    @Unindex
    private String questionDescription;

    private int questionNumber;

    private FeedbackQuestionType questionType;

    private FeedbackParticipantType giverType;

    private FeedbackParticipantType recipientType;

    // Check for consistency in questionLogic/questionAttributes.
    // (i.e. if type is own team, numberOfEntities must = 1).
    private int numberOfEntitiesToGiveFeedbackTo;

    private List<FeedbackParticipantType> showResponsesTo = new ArrayList<>();

    private List<FeedbackParticipantType> showGiverNameTo = new ArrayList<>();

    private List<FeedbackParticipantType> showRecipientNameTo = new ArrayList<>();

    @Translate(InstantTranslatorFactory.class)
    private Instant createdAt;

    @Translate(InstantTranslatorFactory.class)
    private Instant updatedAt;

    @SuppressWarnings("unused")
    private FeedbackQuestion() {
        // required by Objectify
    }

    public FeedbackQuestion(
            String feedbackSessionName, String courseId,
            String questionText, String questionDescription, int questionNumber, FeedbackQuestionType questionType,
            FeedbackParticipantType giverType,
            FeedbackParticipantType recipientType,
            int numberOfEntitiesToGiveFeedbackTo,
            List<FeedbackParticipantType> showResponsesTo,
            List<FeedbackParticipantType> showGiverNameTo,
            List<FeedbackParticipantType> showRecipientNameTo) {

        this.feedbackQuestionId = null; // Auto-generated by Cloud Datastore
        this.feedbackSessionName = feedbackSessionName;
        this.courseId = courseId;
        this.questionText = questionText;
        this.questionDescription = questionDescription;
        this.questionNumber = questionNumber;
        this.questionType = questionType;
        this.giverType = giverType;
        this.recipientType = recipientType;
        this.numberOfEntitiesToGiveFeedbackTo = numberOfEntitiesToGiveFeedbackTo;
        this.showResponsesTo = showResponsesTo == null ? new ArrayList<>() : showResponsesTo;
        this.showGiverNameTo = showGiverNameTo == null ? new ArrayList<>() : showGiverNameTo;
        this.showRecipientNameTo =
                showRecipientNameTo == null ? new ArrayList<>() : showRecipientNameTo;
        this.setCreatedAt(Instant.now());
    }

    public Instant getCreatedAt() {
        return createdAt == null ? Const.TIME_REPRESENTS_DEFAULT_TIMESTAMP : createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt == null ? Const.TIME_REPRESENTS_DEFAULT_TIMESTAMP : updatedAt;
    }

    /**
     * Sets the createdAt timestamp of the question.
     */
    public void setCreatedAt(Instant newDate) {
        this.createdAt = newDate;
        setLastUpdate(newDate);
    }

    public void setLastUpdate(Instant newDate) {
        this.updatedAt = newDate;
    }

    /**
     * Gets the question id.
     */
    public String getId() {
        String untrimmedId = Key.create(FeedbackQuestion.class, feedbackQuestionId).toLegacyUrlSafe();
        String newString = " ";

        // Previously (prior to V8.0.0), the keys are generated by Objectify v5 which uses App Engine Datastore API.
        // Starting from V8.0.0 we use Objectify v6 which uses Cloud Datastore API,
        // and the produced legacy URL-safe key has additional space character at the end of the string,
        // resulting in incompatibility with old data.
        // Last four characters (ogEA) of the base64 encoded string are trimmed for backward compatibility.
        // Additional changes are needed for production system case, possibly due to very old Datastore instance used.
        String[] words=s1.split("ogEA");//splits the string based on string
        for(String w:words){
            newString = newString+w;
        }
        newString=newString.trim();
        return newString.replaceFirst("KIBAA$","AogEA");


    }

    public void setFeedbackQuestionId(Long feedbackQuestionId) {
        this.feedbackQuestionId = feedbackQuestionId;
    }

    public String getFeedbackSessionName() {
        return feedbackSessionName;
    }

    public void setFeedbackSessionName(String feedbackSessionName) {
        this.feedbackSessionName = feedbackSessionName;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public FeedbackQuestionType getQuestionType() {
        return questionType;
    }

    public void setQuestionType(FeedbackQuestionType questionType) {
        this.questionType = questionType;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public FeedbackParticipantType getGiverType() {
        return giverType;
    }

    public void setGiverType(FeedbackParticipantType giverType) {
        this.giverType = giverType;
    }

    public FeedbackParticipantType getRecipientType() {
        return recipientType;
    }

    public void setRecipientType(FeedbackParticipantType recipientType) {
        this.recipientType = recipientType;
    }

    public int getNumberOfEntitiesToGiveFeedbackTo() {
        return numberOfEntitiesToGiveFeedbackTo;
    }

    public void setNumberOfEntitiesToGiveFeedbackTo(
            int numberOfEntitiesToGiveFeedbackTo) {
        this.numberOfEntitiesToGiveFeedbackTo = numberOfEntitiesToGiveFeedbackTo;
    }

    public List<FeedbackParticipantType> getShowResponsesTo() {
        return showResponsesTo;
    }

    public void setShowResponsesTo(List<FeedbackParticipantType> showResponsesTo) {
        this.showResponsesTo = showResponsesTo;
    }

    public List<FeedbackParticipantType> getShowGiverNameTo() {
        return showGiverNameTo;
    }

    public void setShowGiverNameTo(List<FeedbackParticipantType> showGiverNameTo) {
        this.showGiverNameTo = showGiverNameTo;
    }

    public List<FeedbackParticipantType> getShowRecipientNameTo() {
        return showRecipientNameTo;
    }

    public void setShowRecipientNameTo(
            List<FeedbackParticipantType> showRecipientNameTo) {
        this.showRecipientNameTo = showRecipientNameTo;
    }

    /**
     * Updates the updatedAt timestamp when saving.
     */
    @OnSave
    public void updateLastUpdateTimestamp() {
        this.setLastUpdate(Instant.now());
    }
}
