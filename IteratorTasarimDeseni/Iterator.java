class Takim
{
    public string TakimAdi { get; set; }
    public int Puan { get; set; }
}

//Iterator arayüzü
interface ITakimIterator
{
    Takim Next();
    bool IsDone();
    Takim CurrentItem();
}
//Aggregate arayüzü
interface ITakimAggregate
{
    ITakimIterator GetIterator();
}
//ConcreteAggregate
class TakimConcreteAggregate : ITakimAggregate
{
    private List<Takim> _TakimList = new List<Takim>();
    public int TakimCount { get { return _TakimList.Count; } }
    public void Add(Takim t)
    {
        _TakimList.Add(t);
    }
    public Takim GetItem(int index)
    {
        return _TakimList[index];
    }
    public ITakimIterator GetIterator()
    {
        return new TakimConcreteIterator(this);
    }
}
//ConcreteIterator
class TakimConcreteIterator : ITakimIterator
{
    private TakimConcreteAggregate CollectionTakim;
    private int _index = 0;
    public TakimConcreteIterator(TakimConcreteAggregate ColTakim)
    {
        CollectionTakim = ColTakim;
    }
    public Takim Next()
    {
        _index++;
        if (IsDone())
            return CollectionTakim.GetItem(_index);
        else
            return null;
    }
    public bool IsDone()
    {
        return _index < CollectionTakim.TakimCount;
    }
    public Takim CurrentItem()
    {
        return CollectionTakim.GetItem(_index);
    }
}