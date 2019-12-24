//Client
class Program
{
    static void Main(string[] args)
    {
        TakimConcreteAggregate TakimCollection = new TakimConcreteAggregate();
        TakimCollection.Add(new Takim { TakimAdi = "Şalvar Spor", Puan = 59 });
        TakimCollection.Add(new Takim { TakimAdi = "Real Madrid", Puan = 9 });
        TakimCollection.Add(new Takim { TakimAdi = "Barcelona", Puan = 10 });
        ITakimIterator itr = TakimCollection.GetIterator();
        while (itr.IsDone())
        {
            Console.WriteLine("{0}:{1}", itr.CurrentItem().TakimAdi, itr.CurrentItem().Puan);
            itr.Next();
        }
        Console.ReadKey();
    }
}