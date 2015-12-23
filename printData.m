function printData(x,y)
figure
test = xlsread('survival1.xlsx', 'C1:D3000');
x=[1:2000];
y=test(:,1);
n=test(:,2);

for i=[1:2000]
   y(i)=sum(y(i:i+500))/500;
    n(i)=sum(n(i:i+500))/500;
end


plot(x(1:2000),y(1:2000),'-ro',x(1:2000),n(1:2000),'-b')

h_legend=legend('SARSA＇,＇Q-Learning')
set(h_legend,'FontSize',14);

title('AccumlativeReward Per Battle Vs. Number of Battles with Exploratin Rate 0.3 ','FontSize', 20);
%scatter3(x,y,z,'filled')
xlabel('Number of battles','FontSize', 20); % x-axis label
ylabel('Averaged AcummulativeReward','FontSize', 20);% y-axis label

end
