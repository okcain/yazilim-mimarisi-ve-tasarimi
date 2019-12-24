# Yazılım Mimarisi ve Tasarımı

Yazılım Mimarisi ve Tasarımı proje ödevi

# Kavramlar

- [Iterator: Koleksiyon elemanları elde edilebilmesi için gerekli işlemleri tanımlar.](#iterator)
- [Facade: Alt sistemlerin direkt olarak kullanılması yerine alt sistemdeki nesneleri kullanan başka bir nesne üzerinden kullanılmasını sağlar.](#facade)
- [Aggregate: Koleksiyon barındıran nesnelerin Iterator tipinden nesne oluşturacağını belirten arayüzdür.](#aggregate)
- [Concrete Aggregate: Koleksiyon barındıran nesnedir. Aggregate arayüzünü uygular ve ilgili ConcreteIterator nesnesini oluşturur.](#concreteAggregate)
- [ConcreteIterator:  Aggregate yapısında ki koleksiyon elemanlarının elde edilmesini sağlayan metotları barındıran yani Iterator arayüzünü uygulayan gerçek iterator nesnesidir.](#concreteIterator)
- [Client: Bu yapıyı kullanarak koleksiyon içindeki elemanlara erişen yapıdır.](#client)

# Ön Yüz ( Facade ) Tasarım Deseni

  Facade tasarım deseni; structural grubuna ait, alt sistemlerin direkt olarak kullanılması yerine alt sistemdeki nesneleri kullanan başka bir nesne üzerinden kullanılmasını sağlayan tasarım desenidir.

  Facade tasarım deseni uygulanması en basit tasarım desenlerinden birisidir. Örneğin uygulamamızda bazı modüller var ve bazı işlemlerin gerçekleştirilmesi için bu modüllerin kullanılması gerekiyor. Burada facade tasarım deseni kullanılarak clientın yani bu operasyonları gerçekleştirecek nesnenin kod karmaşasına bulaşmamasını sağlar ve farklı clientların olduğu uygulamalarda bu kodların tekrarlanmasını engellemiş olur. Sonuç olarak da anlaşılması daha kolay bir kodlama yapılmış olunur. Facade tasarım deseni için uml diyagramı aşağıdaki gibidir.

![Image of Class](https://github.com/okcain/yazilim-mimarisi-ve-tasarimi/blob/master/facade_uml.png)

  Görüldüğü gibi client alt sisteme direkt erişmeyip, alt sistemi kullanan facade nesnesi üzerinden işlem yapıyor. Clientın olduğu katmanda alt sistem referansı eklemeye gerek yoktur. Facade nin olduğu katmanda alt sistem referansları eklenmelidir.

   Senaryomuz şu şekilde olsun. sistem1 ve sistem2 adında üyelik modülünün olduğu 2 sistemimiz var. sistem2 ye üye olunacağında sistem1 de kara listede olup olmadığını ve kimlik numarasının doğru olup olmadığını kontrol edip üyeliği buna göre kabul ediyoruz.

```C#
public class Sistem1Kontrol
{
    public bool KaraListeKontrol(string Tc)
    {
        //kontrol edildiğini varsayalım
        return false;
    }
}
```

```java
public class Sistem2Operations
{
    public void UyeEkle(string Tc)
    {
        Console.WriteLine("{0} Üye Eklendi",Tc);
    }
}
```

```java
public class TcKimlikSistem
{
    public bool Kontrol(string Tc)
    {
        //kontrol edildiğini varsayalım
        return true;
    }
}
```

  Alt sistemlerin tanımlanmasının ardından facade sınıfımızı oluşturuyoruz.

```java
public class Facade
{
    //constructor da oluşturulabilir
    //singleton olarak tasarlanabilir
    TcKimlikSistem TcSistem = new TcKimlikSistem();
    Sistem1Kontrol Sistem1 = new Sistem1Kontrol();
    Sistem2Operations Sistem2 = new Sistem2Operations();
    public void Sistem2UyeEkle(string Tc)
    {
        if (TcSistem.Kontrol(Tc) && !Sistem1.KaraListeKontrol(Tc))
        {
            Sistem2.UyeEkle(Tc);
        }
    }
}
```

  Ardından Facade sınıfımızı Main sınıfımıza bağlıyoruz. Böylece client bu sınıfları direkt olarak kullanmayıp oluşturulan Facade sınıfı üzerinden kullanıyor.

```java
class Program
{
    static void Main(string[] args)
    {
        Facade f = new Facade();
        f.Sistem2UyeEkle("123123");
        Console.ReadKey();
    }
}
```
# Tekrarlayıcı (Iterator) Tasarım Deseni

  Nesne tabanlı dillerde uygulama geliştirilirken en sık kullanılan yapılardan biri de koleksiyonlardır.  Iterator tasarım deseni koleksiyon yapısı bilinmesine ihtiyaç olmadan koleksiyon elemanları üzerinde işlem yapılabilmesini sağlar. Iterator tasarım deseni kullanılarak koleksiyonun array, queue, list olmasınin bir önemi olmadan, koleksiyon elemanlarının aynı şekilde elde edilmesi sağlanır. Koleksiyon içindeki nesnelerin nasıl elde edileceği tercihe göre belirlenebilir. Iterator tasarım deseni için uml diyagramı aşağıdaki gibidir.

![Image of Class](https://github.com/okcain/yazilim-mimarisi-ve-tasarimi/blob/master/iterator_uml.jpg)

   Iterator arayüzü ile koleksiyon elemanları içinde dolaşılmasını sağlayan metotlar tanımlıdır. Bu metotları ihtiyaca göre de düzenlenebilir.  Aggregate nesnesi ise Iterator tipinden nesne verecek olan metodu tanımlıyor ve ConcreteAggregate nesneleri bu arayüzü uygulayarak ilgili koleksiyon elemanlarında dolaşılmasını sağlayan ConcreteIterator nesnesinin oluşturulmasından sorumlu oluyor. ConcreteIterator nesnesi ise Iterator arayüzünü uygulayarak içinde barındırdığı koleksiyon elemanlarında dolaşılmasını sağlıyor.
   
   Senaryomuz şu şekilde olsun. “Takim” nesnemiz olsun ve içinde “TakimAdi” ve “Puan” özelliklerini barındırsın ve bu nesnenin koleksiyonunu oluşturup nesneleri sırayla elde edelim.
   
   Takim nesnesi kolleksiyonu tutulacak nesnedir. ITakimIterator arayüzü ile Takim nesnesi koleksiyonunda ki elemanları elde etmek için kullanılacak metotları tanımlamış olduk. ITakimAggregate arayüzünde ise koleksiyon sınıfının hangi metot ile Iterator nesnesini üreteceğini belirtmiş olduk. TakimConcreteAggregate sınıfı ise içinde Takim nesnesi koleksiyonu barındıran sınıfımızdır ve ITakimAggregate arayüzünü uygulayarak içinde barındırdığı koleksiyonda dolaşılmasını sağlayacak olan ITakimIterator tipinde nesne örneğini sağlayacağını belirtmiştir. TakimConcreteIterator nesnesi ise kendisine verilen TakimConcreteAggregate içindeki koleksiyonda ki nesnelere erişimi sağlamıştır. Main metodunda ise TakimConcreteAggregate nesnesi oluşturulmuş , içinde barındırdığı Takim listesine elemanlar eklenmiş ve bu elemanlara erişmiştir yani iterator tasarım deseninin “Client” yapısıdır.
