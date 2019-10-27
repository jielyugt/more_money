# HackGT 6 Project
by Bingze Cai, Xiaoying Yang, and Jie Lyu

##Name

More Money - Invest while you shop


##Description

Spending money makes people happy, but how about spending money while saving some for investigation? More Money helps you save and invest while you shop. The inspiration came from an observation, both from our personal experience and our friends, that sometimes we pay extra $$$ for something that is not worth it simply because we feel good for paying that extra $$$. So, we though about why don't we make an app that let people keep that good feeling, but in fact save money for them. In light of this, we made More Money.

More Money is an android mobile app powered by NCR POS and Banking API. It shows the inventory of a merchant using NCR Silver API which can be directly ordered from your mobile app. However, before you place an order for an item with a price of $X, the recommendation engine in our app will come up with a list of similar item with a lower price, say $Y. If you decide to pick one from that list instead, you will still pay $X for that item, but have $X-Y deposited on your investment account. Whenever you want, you will be able to transfer the money saved by our APP to your bank deposit or to invest. 


##Challenge

None of us had experience in Android development so we spent one night just learning how to use Android studio and the MVC structure. The final app was developed on top of a template (https://github.com/ivacf/archi) which gets information from GitHub Repositories.

We had trouble accessing the Silver API, mostly because we are inexperienced in Android development and inexperienced in making API calls in Java. The connection this morning was unstable, so we ended up pulled the JSON file down and made our own local data. Thanks for all the NCR staff that helped us through!


## Instruction
To run this project, download Android Studio and use Android SDK API 25

## Credit
Android Studio template forked from git repository [ivacf/archi](https://github.com/ivacf/archi)
