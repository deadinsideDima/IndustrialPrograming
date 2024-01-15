abstract class AbstractFabric {
    protected String Type;
    protected String Place;
    protected String Amount;

    public AbstractFabric(String Type, String Place, String Amount) {
        this.Type = Type;
        this.Place = Place;
        this.Amount = Amount;
    }

    public abstract String getAmount();

    public abstract String getType();

    public abstract String getPlace();
}

class Fabric extends AbstractFabric {
    public Fabric(String Type, String Place, String Amount) {
        super(Type, Place, Amount);
    }

    @Override
    public String getAmount() {
        return Amount;
    }

    @Override
    public String getType() {
        return Type;
    }

    @Override
    public String getPlace() {
        return Place;
    }
}
