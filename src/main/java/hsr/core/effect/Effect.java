package hsr.core.effect;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME, 
    include = JsonTypeInfo.As.PROPERTY, 
    property = "effect")
@JsonSubTypes({ 
    @Type(value = PrimaryEffect.SingleTarget.class, name = "SINGLE_TARGET"),
    @Type(value = PrimaryEffect.Bounce.class, name = "BOUNCE"),
    @Type(value = PrimaryEffect.Blast.class, name = "BLAST"),
    @Type(value = PrimaryEffect.AreaOfEffect.class, name = "AREA_OF_EFFECT"),
    @Type(value = SecondaryEffect.Buff.class, name = "BUFF"),
    @Type(value = SecondaryEffect.Debuff.class, name = "DEBUFF"),
})
public abstract class Effect {
    protected String name; // If effect has a specific name and needs to be displayed as such.
    protected Recipient recipient; // What targets does this affect.
    protected Trigger triggerFrom; // If effect requires trigger to start.
    protected Trigger triggerFor; // If effect requires a trigger to activate for recipient.
    protected List<Effect> modifiedBy; // If this effect is modified by other effects, like Eidolons or Traces. 
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Recipient getRecipient() {
        return recipient;
    }
    public void setRecipient(Recipient recipient) {
        this.recipient = recipient;
    }
    public Trigger getTriggerFrom() {
        return triggerFrom;
    }
    public void setTriggerFrom(Trigger triggerFrom) {
        this.triggerFrom = triggerFrom;
    }
    public Trigger getTriggerFor() {
        return triggerFor;
    }
    public void setTriggerFor(Trigger triggerFor) {
        this.triggerFor = triggerFor;
    }
    public List<Effect> getModifiedBy() {
        return modifiedBy;
    }
    public void setModifiedBy(List<Effect> modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    @Override
    public String toString() {
        return "Effect [name=" + name + ", recipient=" + recipient + ", triggerFrom=" + triggerFrom + ", triggerFor="
                + triggerFor + ", modifiedBy=" + modifiedBy + "]";
    }
}
