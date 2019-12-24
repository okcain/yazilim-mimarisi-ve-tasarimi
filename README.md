# Yazılım Mimarisi ve Tasarımı

Yazılım Mimarisi ve Tasarımı proje ödevi

# Ön Yüz ( Facade ) Tasarım Deseni

  Facade tasarım deseni; structural grubuna ait, alt sistemlerin direkt olarak kullanılması yerine alt sistemdeki nesneleri kullanan başka bir nesne üzerinden kullanılmasını sağlayan tasarım desenidir.

  Facade tasarım deseni uygulanması en basit tasarım desenlerinden birisidir. Örneğin uygulamamızda bazı modüller var ve bazı işlemlerin gerçekleştirilmesi için bu modüllerin kullanılması gerekiyor. Burada facade tasarım deseni kullanılarak clientın yani bu operasyonları gerçekleştirecek nesnenin kod karmaşasına bulaşmamasını sağlar ve farklı clientların olduğu uygulamalarda bu kodların tekrarlanmasını engellemiş olur. Sonuç olarak da anlaşılması daha kolay bir kodlama yapılmış olunur. Facade tasarım deseni için uml diyagramı aşağıdaki gibidir.

![Image of Class](https://github.com/okcain/yazilim-mimarisi-ve-tasarimi/blob/master/facade_uml.png)

  Görüldüğü gibi client alt sisteme direkt erişmeyip, alt sistemi kullanan facade nesnesi üzerinden işlem yapıyor. Clientın olduğu katmanda alt sistem referansı eklemeye gerek yoktur. Facade nin olduğu katmanda alt sistem referansları eklenmelidir.

   Senaryomuz şu şekilde olsun. sistem1 ve sistem2 adında üyelik modülünün olduğu 2 sistemimiz var. sistem2 ye üye olunacağında sistem1 de kara listede olup olmadığını ve kimlik numarasının doğru olup olmadığını kontrol edip üyeliği buna göre kabul ediyoruz.

```java
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
