class FabricMetadata {
    private String metadata;

    public FabricMetadata(String metadata) {
        this.metadata = metadata;
    }

    public String getMetadata() {
        return metadata;
    }
}

class FabricMetadataDecorator extends AbstractFabric {
    private AbstractFabric fabric;
    private FabricMetadata metadata;

    public FabricMetadataDecorator(AbstractFabric fabric, String metadata) {
        super(fabric.getType(), fabric.getPlace(), fabric.getAmount());
        this.fabric = fabric;
        this.metadata = new FabricMetadata(metadata);
    }

    public FabricMetadata getMetadata() {
        return metadata;
    }

    @Override
    public String getType() {
        return fabric.getType();
    }

    @Override
    public String getPlace() {
        return fabric.getPlace();
    }

    @Override
    public String getAmount() {
        return fabric.getAmount();
    }
}

class FabricBuilder {
    private String type;
    private String place;
    private String amount;
    private static FabricBuilder instance;

    private FabricBuilder() {
        // Private constructor to prevent instantiation outside this class
    }

    public static FabricBuilder getInstance() {
        if (instance == null) {
            instance = new FabricBuilder();
        }
        return instance;
    }

    public FabricBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public FabricBuilder setPlace(String place) {
        this.place = place;
        return this;
    }

    public FabricBuilder setAmount(String amount) {
        this.amount = amount;
        return this;
    }

    public Fabric build() {
        return new Fabric(type, place, amount);
    }
}
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
