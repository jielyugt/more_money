# HackGT 6 Project
by Bingze Cai, Xiaoying Yang, and Jie Lyu

## Name

More Money - Invest while you shop


## Inspiration

Spending money makes people happy, but how about spending money while saving some for investing? More Money helps you save and invest while you shop. The inspiration came from an observation, both from our personal experience and our friends, that sometimes we pay extra $$$ for something that is not worth it simply because we feel good when spending money. So, we though why don't we make an app that make people feel good when they spend, and save the money for them for investment. In light of this, we made More Money.

# Description

More Money is an android mobile app powered by NCR POS and Banking API. It shows the inventory of a merchant using NCR Silver API which can be directly ordered from your mobile app. However, before you place an order for an item with a price of $X, the recommendation engine in our app will come up with a list of similar item with a lower price. If you decide to pick one with price $Y from that list instead (Y is always < X), you will still pay $X for that item you just picked, but have $X-Y deposited on your investment account. Whenever you want, you can transfer the money saved by our APP to your bank deposit or to invest.

## Challenge

None of us had experience in Android development so we spent one night just learning how to use Android studio and the MVC structure.

We had trouble accessing the Silver API, mostly because we are inexperienced in Android development and inexperienced in making API calls in Java. The connection this morning was unstable, so we ended up pulled the JSON file down and made our own local data. Thanks for all the NCR staff that helped us through!

## Instruction
To run this project, download Android Studio, check out this repo, and configure Android SDK API 25, click Build and Run on virtual environment.

## Credit
Android Studio template forked from git repository [ivacf/archi](https://github.com/ivacf/archi)
