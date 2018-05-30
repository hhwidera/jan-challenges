package com.widera.janchallenges.blatt03;

public class HouseBucketLink {

    private HouseBucket bucketLeft;
    private HouseBucket bucketRight;

    public HouseBucketLink(final HouseBucket bucketLeft, final HouseBucket bucketRight) {
        this.bucketLeft = bucketLeft;
        this.bucketRight = bucketRight;
        this.bucketLeft.setLinkToRight(this);
        this.bucketRight.setLinkToLeft(this);
    }

    public int getDifference() {
        return this.bucketRight.houses().first().number() - this.bucketLeft.houses().last().number();
    }

    public int badLuckForBothBucketsTogether() {
        HouseBucket tmpBucket = new HouseBucket();
        if (this.bucketLeft != null) {
            tmpBucket.houses().addAll(this.bucketLeft.houses());
        }
        if (this.bucketRight != null) {
            tmpBucket.houses().addAll(this.bucketRight.houses());
        }
        return (int) tmpBucket.badLuck();
    }

    public void joinBuckets() {
        this.bucketLeft.houses().addAll(this.bucketRight.houses());
        this.bucketRight.invalid();
        if (this.bucketRight != null && this.bucketRight.getLinkToRight() != null) {
            this.bucketRight.getLinkToRight().bucketLeft = this.bucketLeft;
        }
    }

    public boolean hasLinkTwoValidBuckets() {
        return this.bucketLeft.isValid() && this.bucketRight.isValid();
    }

    @Override
    public String toString() {
        return "[" + this.bucketLeft + " --(" + this.getDifference() + ")-> " + this.bucketRight + "]";
    }
}
