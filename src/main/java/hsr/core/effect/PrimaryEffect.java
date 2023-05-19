package hsr.core.effect;

public abstract class PrimaryEffect extends Effect {
    protected Scaling scaling; // Scaling for the effect, if any.
    public Scaling getScaling() {
        return scaling;
    }
    public void setScaling(Scaling scaling) {
        this.scaling = scaling;
    }
    @Override
    public String toString() {
        return "PrimaryEffect [" + super.toString() + ", scaling=" + scaling + "]";
    }

    public static class SingleTarget extends PrimaryEffect {
        @Override
        public String toString() {
            return "SingleTarget [" + super.toString() + "]";
        }
    }

    public static class Bounce extends PrimaryEffect {
        private int bounces;
        private int reducedEffectivenessPerBounce;
        public int getBounces() {
            return bounces;
        }
        public void setBounces(int bounces) {
            this.bounces = bounces;
        }
        public int getReducedEffectivenessPerBounce() {
            return reducedEffectivenessPerBounce;
        }
        public void setReducedEffectivenessPerBounce(int reducedEffectivenessPerBounce) {
            this.reducedEffectivenessPerBounce = reducedEffectivenessPerBounce;
        }
        @Override
        public String toString() {
            return "Bounce [" + super.toString() + ", bounces=" + bounces + ", reducedEffectivenessPerBounce=" + reducedEffectivenessPerBounce
                    + "]";
        }
    }

    public static class Blast extends PrimaryEffect {
        private Scaling adjacentScaling;
        public Scaling getAdjacentScaling() {
            return adjacentScaling;
        }
        public void setAdjacentScaling(Scaling adjacentScaling) {
            this.adjacentScaling = adjacentScaling;
        }
        @Override
        public String toString() {
            return "Blast [" + super.toString() + ", adjacentScaling=" + adjacentScaling + "]";
        } 
    }

    public static class AreaOfEffect extends PrimaryEffect {
        @Override
        public String toString() {
            return "AreaOfEffect [" + super.toString() + "]";
        }
    }
}