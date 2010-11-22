K = 6;
S = load('kmeansdata.txt');
EKS = [];
EK = bitmax;
C = 0;

%run kmeans 10-times and pick the best
for i = [1:10]
  [CTMP,EKTMP] = kmeans(S,K);
  EKS = [EKS EKTMP];
  if(EKTMP < EK)
      EK=EKTMP;
      C = CTMP;
  end
  
end
EKS
fprintf('best ek out of 10 iterations: %f\n',EK)
%plot the best
styles = [ '+b';'ob';'*b';'.b';'xb';'sb';'db'];
for i = [1:K]   
   X1 = S(C==i,1);
   X2 = S(C==i,2);   
   plot(X1,X2,styles(i,:))
   hold on
 
end
hold off


